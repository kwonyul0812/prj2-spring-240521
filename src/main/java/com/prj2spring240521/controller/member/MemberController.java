package com.prj2spring240521.controller.member;


import com.prj2spring240521.domain.member.Member;
import com.prj2spring240521.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService service;

    @PostMapping("signup")
    public ResponseEntity signup(@RequestBody Member member) {
        if (service.validate(member)) {
            service.add(member);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "check", params = "email")
    public ResponseEntity checkEmail(@RequestParam("email") String email) {
//        System.out.println("eamil = " + email);
        Member member = service.getByEmail(email);
        if (member == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(email);
    }

    @GetMapping(value = "check", params = "nickName")
    public ResponseEntity checkNickName(@RequestParam("nickName") String nickName) {
//        System.out.println("nickName = " + nickName);
        Member member = service.getByNickName(nickName);

        if (member == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(nickName);
    }

    @GetMapping("list")
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    public List<Member> list() {
        return service.list();
    }

    @GetMapping("{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity get(@PathVariable Integer id,
                              Authentication authentication) {
        if (!service.hasAccess(id, authentication)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Member member = service.getById(id);
        if (member == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(member);
        }
    }

    @DeleteMapping("{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity delete(
            @RequestBody Member member,
            Authentication authentication) {
        if (service.hasAccess(member, authentication)) {
            service.remove(member.getId());
            return ResponseEntity.ok().build();
        }

        // todo : forbidden으로 수정하기
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PutMapping("modify")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity modify(@RequestBody Member member,
                                 Authentication authentication) {
        if (service.hasAccessModify(member, authentication)) {
            service.modify(member);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PostMapping("token")
    public ResponseEntity token(@RequestBody Member member) {
        Map<String, Object> map = service.getToken(member);

        if (map == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(map);
    }

}
