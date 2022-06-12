package com.osias10.shuttlerserver.controller;

import com.google.gson.JsonObject;
import com.osias10.shuttlerserver.domain.entity.AuthRequest;
import com.osias10.shuttlerserver.dto.MemberDto;
import com.osias10.shuttlerserver.service.MemberService;
import com.osias10.shuttlerserver.util.JwtUtil;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;


@Controller
@AllArgsConstructor
public class MemberController {
    private MemberService memberService;
    
    private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;
    
    // 메인 페이지
    @GetMapping("/")
    public String index() {
    	System.out.println("go to index");
        return "index";
    }

    // 회원가입 페이지
    @GetMapping("/user/signup")
    public String dispSignup() {
        return "signup";
    }

    // 회원가입 처리
    @PostMapping("/user/signup")
    public String execSignup(MemberDto memberDto) {
        memberService.joinUser(memberDto);

        return "redirect:/user/login";
    }

    // 로그인 페이지
    @GetMapping("/user/login")
    public String dispLogin() {
        return "login";
    }

    // 로그인 결과 페이지
    @ResponseBody
    @GetMapping("/user/login/result")
    public String dispLoginResult() {
    	JsonObject obj =new JsonObject();
    	obj.addProperty("http status code", 200);
    	obj.addProperty("message", "Success");
    	return obj.toString();
        //return "/loginSuccess";
    }

    // 로그아웃 결과 페이지
    @GetMapping("/user/logout/result")
    public String dispLogout() {
        return "/logout";
    }

    // 접근 거부 페이지
    @GetMapping("/user/denied")
    public String dispDenied() {
        return "/denied";
    }

    // 내 정보 페이지
    @GetMapping("/user/info")
    public String dispMyInfo() {
        return "/myinfo";
    }

    // 어드민 페이지
    @GetMapping("/admin")
    public String dispAdmin() {
        return "/admin";
    }
    /*
    @PostMapping("/api/login")
    public String apiLogin(MemberDto memberDto) {
        memberService.joinUser(memberDto);

        return "redirect:/user/login";
    }
    */
    @GetMapping("/api/login")
    public String dispLoginResult2() {
    	JsonObject obj =new JsonObject();
    	obj.addProperty("http status code", 200);
    	obj.addProperty("message", "Success");
    	return obj.toString();
        //return "/loginSuccess";
    }
    @ResponseBody
    @PostMapping("/api/login")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
    	System.out.println("testid:"+authRequest);
        try {
            authenticationManager.authenticate(
            		
                    new UsernamePasswordAuthenticationToken(authRequest.getId(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        String token = jwtUtil.generateToken(authRequest.getId());
        //turn token;
        JsonObject obj =new JsonObject();
    	obj.addProperty("http status code", 200);
    	obj.addProperty("message", "Success");
    	obj.addProperty("token",token);
    	return obj.toString();

    }
    @ResponseBody
    @PostMapping("/test/api")
    //@RequestMapping(value = "/test/api", method = RequestMethod.POST)
    public String apiTest() {
    	JsonObject obj =new JsonObject();
    	obj.addProperty("http status code", 200);
    	obj.addProperty("message", "test");
    	return obj.toString();
        //return "apitest";
    }
    
    @ResponseBody
    @GetMapping("/test/apiget")
    //@RequestMapping(value = "/test/api", method = RequestMethod.POST)
    public String apiTest2() {
    	JsonObject obj =new JsonObject();
    	obj.addProperty("http status code", 200);
    	obj.addProperty("message", "test");
    	return obj.toString();
        //return "apitest";
    }
    
}