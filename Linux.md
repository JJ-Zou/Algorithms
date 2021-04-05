
#### 文件系统

文件系统组成

- 目录：文件名和inode table
- Inode：每个文件都有对应的Inode，包含文件的元信息：字节数、拥有者id、group id、权限、时间戳（inode上次变动，文件上次变动、文件上次打开）、链接数（多少文件名指向Inode）、文件数据block位置、blocks快数、block块大小、device设备号，`stat [filename]`查看文件inode信息
- block：记录文件内容，大文件会占用多个block
- super block：记录文件的整体信息，inode和block总量、使用量、剩余量等
- block bitmap：记录block是否被使用的位图

#### 文件操作

inode有12个直接指针，指向4K大小的块，间接指针、双间接指针、三间接指针。每个inode固定大小（128bytes、256bytes），每个文件仅会占用一个inode。

读取文件时，现在inode中查找文件内容所在的block，然后把所有的block读取出来。

建立目录时，会分配一个inode和至少一个block，block记录目录下所有文件的文件名和inode。inode本身不记录文件名，文件名记录在目录中，因此增删改文件名与目录的写权限有关。
