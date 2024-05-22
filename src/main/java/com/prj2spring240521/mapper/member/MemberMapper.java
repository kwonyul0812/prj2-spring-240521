package com.prj2spring240521.mapper.member;

import com.prj2spring240521.domain.member.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MemberMapper {

    @Insert("""
            INSERT INTO member (email, password, nick_name)
            VALUES (#{email}, #{password}, #{nickName})
            """)
    int insert(Member member);

    @Select("""
            SELECT * 
            FROM member
            WHERE email = #{email}
            """)
    Member selectByEmail(String email);

    @Select("""
            SELECT *
            FROM member
            WHERE nick_name = #{nickName}
            """)
    Member selectByNickName(String nickName);

    @Select("""
            SELECT *
            FROM member
            ORDER BY id DESC
            """)
    List<Member> selectMember();
}
