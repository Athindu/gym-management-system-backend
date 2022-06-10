package com.coursework.gymmanager.repository;

import com.coursework.gymmanager.model.DefaultMember;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends MongoRepository<DefaultMember,String> {

}
