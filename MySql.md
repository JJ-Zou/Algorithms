
#### 范式

- 第一范式：确保每列都是不可分割的原子属性
- 第二范式：每个非主键完全依赖于主键
- 第三范式：每列都与主键之间相关，而不是传递依赖于主键

#### 事务的特性

- Atomicity原子性：事务为不可分割的最小单元，一个事务的所有操作要么全部成功提交，要么全部失败回滚。通过undo log实现。
- Consistency一致性：保证事务只能把数据库从一个有效正确状态转移为另一个有效正确的状态。需要应用层规范有效正确的状态实现，依赖于其它特性。
- Isolation隔离性：一个事务在提交之前，所做的修改对其他事务不可见。通过读写锁+MVCC实现。
- Durability持久性：一旦事务提交所做的修改将会永远保存到数据库中，即使系统奔溃也不会丢失。通过redo log实现。

#### 并发访问导致的数据地区问题

- 脏读：当前事务可以读到其他事务未提交的数据，如果其他事务进行回滚，当前事务读到的数据就是脏数据。
- 不可重复读：在同一个事务中，多次读取一条数据，由于其他事务对该数据进行修改提交，导致多次读取得到不一样的结果。
- 幻读：在同一事务中，读取到其他事务新插入的行，导致多次读取数据的数量不同。

#### 隔离级别

- 读未提交：可以读取其他事务未提交的数据
- 读已提交：事务只能读取其他事务已提交的数据
- 可重复读：保证在同一个事务中多次读取统一数据的结果一致。
- 可串行化：强制事务串行执行

#### Innodb读写锁与意向锁

Innodb支持行锁和表锁，锁定的数据越少，数据库的并发度就越高。

**读写锁行锁**

- 排他锁（X锁）：其他事务不能写也不能读
- 共享锁（S锁）：其他事务可以读取，但不能写

**意向锁表锁**

- 意向排他锁（IX锁）：事务在获得数据行的X锁前，必须先获得表的IX锁
- 意向共享锁（IS锁）：事务在获得数据行的S锁前，必须先获得表达IS锁或IX锁

- 对于update、delete和insert语句，Innodb自动加X锁
- 对于普通select语句，不会添加任何所
- 事务可以通过lock in share mode添加S锁，for update添加X锁
- 意向锁由Innodb引擎自动添加
- 通过意向锁，事务想要对表加X锁，只需要检查表上是否加了意向锁或表锁，如果存在，事务会被阻塞。任意意向锁间都是兼容的，意向锁和行锁也是兼容的。
- 事务执行根据两阶段锁协议，根据隔离级别自动加锁，锁只有在commit或rollback时才会释放，而且是同一时刻释放

**行锁实现方式**

- 行锁是通过给索引加锁实现的，只有通过索引检索，才会加行锁，否则会进行全表扫描，对所有数据行加锁。
- 只有执行计划真正使用到索引，才会使用行锁
- 如果索引存在相同值，由于行锁锁住的是索引，可能会导致锁住多行

#### 间隙锁

使用范围条件而不是相等条件检索数据，会在对索引范围加锁，间隙可以跨多个索引记录，间隙锁可以防止其他事务在这个范围进行插入或修改，保证两次读取该范围内的记录不会变，防止幻读。

#### next key lock

next key锁是间隙锁和行锁的组合，指的是加在某索引及之前索引上的锁，加锁为左开右闭区间。查找过程中访问到的对象才会加锁，范围查询会访问到不满足条件的第一个值为止。

#### 插入意向锁

在insert操作时产生，多事务同时写入不同数据至同一索引间隙内时，如果插入的位置不冲突不会相互阻塞。

#### 悲观锁和乐观锁

- 悲观锁认为数据总会产生并发冲突，需要对请求的数据枷锁。通过数据库锁实现，在对查询语句添加for update加上一个写锁。
- 乐观锁认为大多数情况下不会产生冲突，只有在数据库更新提交时才会对数据做冲突检测，检查数据是否被修改。乐观锁一般使用版本号或时间戳实现，在提交更新的时候检查修改时写入的版本号或时间戳与当前的是否一致，如果不一致表明是过期数据。

#### 多版本并发控制

MVCC和next key lock解决了数据库的幻读问题。加锁可以解决并发一致性问题，MVCC使用了多个版本，在写操作时更新最新的版本快照，而读操作读旧版本快照。在事物的修改操作时会为数据行新增一个版本快照，并把当前事务id写入隐藏列trx_id，MVCC规定只能读取已提交的快照。

- 系统版本号SYS_ID（全局）：每开始一个事务，系统版本号就会递增
- 事务版本号TRX_ID（隐藏列）：事务开始时的版本号
- 回滚指针roll_pointer（隐藏列）：对记录改动时，会把旧版本快照写入undo log，回滚指针指向旧记录的版本，对同一条记录的更新，会把旧值放入undo log作为旧版本记录，多次更新会被roll_pointer连接成一个链表

**版本读取**

使用快照记录开始但未提交的事务列表，记录每个事务的id，最小id和最大id

- 如果记录的trx_id小于最小id，说明该记录是已提交的，对其他事务可见
- 如果记录的trx_id大于最大id，说明记录在快照之后生成，不可见
- 如果记录处于之间，则需要根据隔离级别判断，对于可重复读，该情况不可见
- 如果当前记录不可见，就沿着回滚指针找到旧版本的下一个快照再进行判断

MVCC的select是快照读，读取的是快照中的数据，不需要进行加锁，而修改操作是当前读需要加锁读取最新数据。

#### redo log

是在数据库发生意外时保证数据库的持久性，进行数据恢复，redo log记录事务执行过程中的修改数据，属于物理日志，重启mysql时会读取redo log将未写入磁盘数据写到磁盘永久保存。

#### undo log

数据库原子性通过undo log实现，用于将未提交的事务回滚到事务开始之前，undo日志记录修改操作，是一种逻辑日志。

#### bin log和relay log

用于主从复制，主库在每个事务提交时将操作记录串行写入binlog，从库开启一个IO线程读取bin log将数据同步到从库的relay日志，从库在使用SQL线程读取relay log顺序执行日志中的SQL事件，从而与主库保持一致。

#### 索引

**B+树索引**

- B+树的每个结点都是一个页
- 非叶子节点存放多个索引行，每个索引行存储索引键和指向下一层结点的指针
- 叶子节点存放键值和数据，叶子节点连成一个单链表
- 由于B+树索引，非叶子节点不存数据，异常一个页中可容纳的关键字就更多，树更加矮胖，磁盘操作更少。
- 由于叶子节点是链表结构，可以进行索引顺序查找，范围查找效率更高，而B树需要通过中序遍历才能进行范围查找
- B+树每次查询都会到达叶子结点，查询更加稳定

**哈希索引**

- 哈希索引可以以O(1)的时间复杂度查找
- 哈希索引不能进行排序和分组
- 只能精确查找，无法进行范围查找
- 当某个索引值被频繁访问时，会在B+树索引上创建一个自适应哈希索引，可以进行快速精确查找

#### 索引分类

- 聚簇索引：按照每张表的主键构造一棵B+树，叶子节点存放整张表的记录。
- 非聚簇索引（辅助索引）：非主键索引，叶子节点存放索引键和主键索引值，查找时，先在辅助索引上查找到主键值，再回表从聚簇索引上根据主键值查找。

**覆盖索引**

指一个查询语句的结果只需要从索引中就能获取，而不需要从数据表获得。对于非聚簇索引，如果能够覆盖索引，就不需要回表再去聚簇索引上查找。

**联合索引**

创建联合索引时，根据where子句把识别度最高的字段放在前面，由于B+树只能存放一个key值，联合索引遵循最左匹配原则，只有用到联合索引最左边的字段进行查询才会走联合索引。联合索引遇到范围查找后就会停止匹配。

#### 查询优化

使用explain分析查询语句。
- select_type：表示查询类型
- type：表示使用那种类型：const>eq_ref>ref>range>index>all，如果遇到index或all就可能需要进行优化
- key：使用到的索引，如果为null则表示未使用索引
- ref：显示索引的哪一列被使用

#### Innodb和MyISAM比较

- Innodb支持事务
- Innodb支持行锁和表锁，MyISAM仅支持表锁
- Innodb支持外键