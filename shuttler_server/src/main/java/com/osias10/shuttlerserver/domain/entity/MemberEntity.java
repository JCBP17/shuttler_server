package com.osias10.shuttlerserver.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "UserDB")

public class MemberEntity {

	@Id
    private String uuid;
	
	@Column(length = 10, nullable = false)
    private String name;

    @Column(length = 30, nullable = false)
    private String id;

    @Column(length = 100, nullable = false)
    private String password;
    

    @Builder
    public MemberEntity(String uuid,String name, String id, String password) {
    	this.uuid=uuid;
    	this.name=name;
        this.id = id;
        this.password = password;
    }
}
