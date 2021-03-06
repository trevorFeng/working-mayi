package com.mayi.transfer.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("third_party_client")
public class ThirdPartyClient implements Serializable {
    private static final long serialVersionUID=1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String clientName;

    private String clientId;
}
