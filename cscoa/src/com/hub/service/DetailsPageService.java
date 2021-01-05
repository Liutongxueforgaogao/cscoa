package com.hub.service;

import java.util.ArrayList;

import com.hub.entity.DetailsInformation;
import com.hub.entity.InformationExm00101;
/**
 * 取详细信息【20170512】：现在改为直接从DAO直接拿数据，详细信息的读取不必经过service
 * @author winv87
 */
public interface DetailsPageService {
	DetailsInformation getAllDetailsInformation(String checkid);
}
