package com.example.laboratorinis.mybatis.dao;

import java.util.List;

import com.example.laboratorinis.mybatis.model.Author;
import org.mybatis.cdi.Mapper;

public interface AuthorMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.AUTHOR
     *
     * @mbg.generated Tue Apr 11 21:21:15 EEST 2023
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.AUTHOR
     *
     * @mbg.generated Tue Apr 11 21:21:15 EEST 2023
     */
    int insert(Author row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.AUTHOR
     *
     * @mbg.generated Tue Apr 11 21:21:15 EEST 2023
     */
    Author selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.AUTHOR
     *
     * @mbg.generated Tue Apr 11 21:21:15 EEST 2023
     */
    List<Author> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.AUTHOR
     *
     * @mbg.generated Tue Apr 11 21:21:15 EEST 2023
     */
    int updateByPrimaryKey(Author row);
}