select * from vm_employee;

create view vw_employee
as
select *
from employee e join title t on e.title = t.tno 
left join employee m on e.manager = m.empno
join department d on e.dept =d.deptNo ;


select e.*, t.* m.empno, m.empname, d.*
from employee e join title t on e.dept =t.tno 
left join employee m on e.manager =m.empno 
join department d on e.dept =d.deptNo ;

select * from employee;

create or replace view vw_full_employee
as
select e.empno,
	   e.empname,
       t.tno as title_no, 
       t.tname as title_name, 
       e.manager as	manager_no,
       m.empname as manager_name,
       e.salary,
       d.deptNo,
       d.deptName,
       d.floor
from employee e join title t on e.title=t.tno
left join employee m on e.manager =m.empno 
join department d on e.dept = d.deptNo;

select empno,empname,title_no,title_name,manager_no,
manager_name,salary,deptNo,deptName,floor 
from vw_full_employee ;

select * from title;

select * from department;