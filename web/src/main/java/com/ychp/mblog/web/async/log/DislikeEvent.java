package com.ychp.mblog.web.async.log;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yingchengpeng
 * @date 2018/8/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DislikeEvent implements Serializable {

	private Long aimId;

	private Integer type;
}
