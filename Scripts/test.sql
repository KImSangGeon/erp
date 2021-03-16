select * from title;
select * from employee;
select * from department;
-- 해당 직책을 가지고 있는 사원목록을 검색
select empname, empno
  from employee e join title t 
    on e.title = t.tno 
 where tno = 3;

select empname, empno
  from employee e join department d  
    on e.dept = d.deptNo 
 where deptNo = 1;

select empno,empname
 from employee e join department d 
 	on e.dept  = d.deptNo ;

select * from title;
select * from department;
select * from employee;
delete from employee where empno = 1004;

select * 
from employee 
where empno = 1003;

select * from employee;

--  pass 길이 확인
--  단반향 함수(Hash :MD5)
select password('aasadsadsadsadsdsaddsadsaa'), length(password('aaaasdsadsadsadadsadsadasdsa')) from dual;


