package com.hcycom.jhipster.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hcycom.jhipster.domain.Authority;


@Mapper
public interface AuthorityMapper {

	/**
	 * 根据uuid获取权限信息
	 * @param uuid
	 * @return
	 */
	@Select("select * from Authority where uuid=#{uuid}")
	public Authority getAuthorityByUuid(@Param("uuid")int uuid);
	/**
	 * 
	 * @return
	 */
	@Select("select * from Authority where authority_type=4")
	public List<Authority> getAllAuthorityByInterface();
	
}
