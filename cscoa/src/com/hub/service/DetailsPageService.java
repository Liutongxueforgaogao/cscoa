package com.hub.service;

import java.util.ArrayList;

import com.hub.entity.DetailsInformation;
import com.hub.entity.InformationExm00101;
/**
 * ȡ��ϸ��Ϣ��20170512�������ڸ�Ϊֱ�Ӵ�DAOֱ�������ݣ���ϸ��Ϣ�Ķ�ȡ���ؾ���service
 * @author winv87
 */
public interface DetailsPageService {
	DetailsInformation getAllDetailsInformation(String checkid);
}
