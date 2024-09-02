package com.iiht.repository;

import com.iiht.entity.Student;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

//public interface UserRepository extends JpaRepository<User,Integer> {
//    User findByUserName(String username);
//}
public interface StudentRepository extends CrudRepository<Student, Integer>
{

    public Student findByEmail(String email);

    public Student findByUsername(String username);

    public Student findByEmailAndPassword(String email, String password);

    public List<Student> findProfileByEmail(String email);

    public boolean existsByUsername(String username);

}