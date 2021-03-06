package com.mayi.transfer.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("receive_account")
public class ReceiveAccount implements Serializable {
    private static final long serialVersionUID=1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private BigDecimal balance;

    private Integer version;
}
