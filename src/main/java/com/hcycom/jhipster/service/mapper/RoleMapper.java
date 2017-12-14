package com.hcycom.jhipster.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hcycom.jhipster.domain.Role;

@Mapper
public interface RoleMapper {

	/**
	 * 添加角色信息
	 * @param role
	 * @return
	 */
	@Insert(value="INSERT INTO role(role_name,role_desc) VALUES " + "(#{role.role_name},#{role.role_desc})")
	public int addRole(@Param("role") Role role);

	/**
	 * 修改角色信息
	 * @param role
	 * @return
	 */
	@Update("update role set role_name = #{role.role_name} , role_desc = #{role.role_desc} where uuid=#{role.uuid}")
	public int updateRole(@Param("role") Role role);

	/**
	 * 根据uuid删除角色信息
	 * @param role
	 * @return
	 */
	@Delete("delete from role where uuid=#{role.uuid}")
	public int deleteRole(@Param("role") Role role);

	/**
	 * 根据uuid查询角色信息
	 * @param roleid
	 * @return
	 */
	@Select("select * from role where uuid=#{roleid}")
	public Role getUsersAuthority(@Param("roleid") int roleid);
	

	/**
	 * 根据角色名称查询角色信息
	 * @param roleid
	 * @return
	 */
	@Select("select * from role where role_name=#{role_name}")
	public Role getRoleByRole_name(@Param("role_name") String role_name);

	/**
	 * 查询所有角色
	 * @return
	 */
	@Select("select * from role")
	public List<Role> getAllAuthority();

}
