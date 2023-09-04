package com.ronnyz.dao.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


/***
 *
 * 2020-06-05
 * @author 王老实
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode
public class User {
    @Id
    private Integer id;
    private String username;
    private Integer age;
}
