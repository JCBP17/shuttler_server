package com.osias10.shuttlerserver.dto;

import com.osias10.shuttlerserver.domain.entity.MemberEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDto {

	private String uuid;
	private String name;
	private String id;
    private String email;
    private String password;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public MemberEntity toEntity(){
        return MemberEntity.builder()
        		.uuid(uuid)
        		.name(name)
                .id(id)
                .password(password)
                .build();
    }

    @Builder
    public MemberDto(String uuid,String name, String id, String password) {
    	this.uuid=uuid;
    	this.name=name;
        this.id = id;
        this.password = password;
    }
}
