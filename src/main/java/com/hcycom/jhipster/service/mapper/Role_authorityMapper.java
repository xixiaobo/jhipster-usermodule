package com.hcycom.jhipster.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hcycom.jhipster.domain.Role_authority;


@Mapper
public interface Role_authorityMapper {

	/**
	 * 添加角色权限信息
	 * @param Role_authority
	 * @return
	 */
	@Insert(value = "INSERT INTO Role_authority(role_uuid,authority_uuid) VALUES "
			+ "(#{role_authority.role_uuid},#{role_authority.authority_uuid})")
	public int addRole_authority(@Param("role_authority") Role_authority role_authority);

	
	/**
	 * 根据uuid删除角色信息
	 * @param Role_authority
	 * @return
	 */
	@Delete("delete from Role_authority where uuid=#{uuid}")
	public int deleteRole_authority(@Param("uuid")int uuid);
	
	/**
	 * 根据角色的uuid删除角色的所属权限
	 * @param Role_authority
	 * @return
	 */
	@Delete("delete from Role_authority where role_uuid=#{roleuuid}")
	public int deleteRole_authorityByRole(@Param("roleuuid")int roleuuid);

	/**
	 * 根据角色id和权限id查询权限关系
	 * @param Role_authorityid
	 * @return
	 */
	@Select("select * from role_authority where role_uuid=#{role_authority.role_uuid} and authority_uuid = #{role_authority.authority_uuid}")
	public Role_authority getRole_authorityByRole_uuidAndAuthority_uuid(@Param("role_authority") Role_authority role_authority);
	

	/**
	 * 根据id查询角色权限
	 * @param Role_authorityid
	 * @return
	 */
	@Select("select * from Role_authority where uuid=#{uuid}")
	public Role_authority getRole_authorityByUuid(@Param("uuid") int uuid);

	/**
	 * 查询所有角色权限
	 * @return
	 */
	@Select("select * from Role_authority")
	public List<Role_authority> getAllRole_authority();
	

	@Select("select * from Role_authority where role_uuid=#{role_uuid}")
	public List<Role_authority> getAllRole_authorityByRole_uuid(@Param("role_uuid")int role_uuid);

}
