package com.osias10.shuttlerserver.service;

import com.osias10.shuttlerserver.domain.entity.MemberEntity;
import com.osias10.shuttlerserver.domain.repository.MemberRepository;
import com.osias10.shuttlerserver.dto.MemberDto;
import lombok.AllArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {
    private MemberRepository memberRepository;

    @Transactional
    public String joinUser(MemberDto memberDto) {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
    	//memberDto.setPassword(memberDto.getPassword());
        String uuid = UUID.randomUUID().toString();
        memberDto.setUuid(uuid);

        return memberRepository.save(memberDto.toEntity()).getId();
    }
/*
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Optional<MemberEntity> userEntityWrapper = memberRepository.findByid(id);
        MemberEntity userEntity = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("admin@example.com").equals(id)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        return new User(userEntity.getId(), userEntity.getPassword(), authorities);
    }
    */
    
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

    	Optional<MemberEntity> userEntityWrapper = memberRepository.findByid(id);
    	MemberEntity userEntity = userEntityWrapper.get();
    	
    	List<GrantedAuthority> authorities = new ArrayList<>();
    	
        if(userEntity==null) throw new UsernameNotFoundException(String.format("ID : [%s]를 찾을 수 없습니다", id));
        else {
        	return new User(userEntity.getId(), userEntity.getPassword(), authorities);
        	
        }
        
    }
    
}