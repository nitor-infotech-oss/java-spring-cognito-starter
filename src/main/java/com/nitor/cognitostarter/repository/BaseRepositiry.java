package com.nitor.cognitostarter.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.nitor.cognitostarter.entity.BaseEntity;


public interface BaseRepositiry extends JpaRepository<BaseEntity, Integer> {

}
