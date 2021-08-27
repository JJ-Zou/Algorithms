select (select name from Subject_Info where id = b.subject_id) as subject_name, count(a.gender) as girl_count
from Student_Info as a
         left join Subject_Register as b
                   on a.id = b.student_id
where a.gender = 2
group by b.subject_id
order by count(a.gender) desc
limit 3;