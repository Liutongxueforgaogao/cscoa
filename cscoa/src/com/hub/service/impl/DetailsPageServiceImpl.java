package com.hub.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hub.dao.DetailsInformationDao;
import com.hub.entity.DetailsInformation;
import com.hub.entity.Rtm001;
import com.hub.entity.Rtm00101;
import com.hub.entity.Rtm002;
import com.hub.entity.Rtm00201;
import com.hub.service.DetailsPageService;
@Service("DetailsPageServiceImpl")
public class DetailsPageServiceImpl implements DetailsPageService {

	@Autowired(required = false) 
	DetailsInformationDao detailsInformationDao;
	
	public DetailsInformation getAllDetailsInformation(String checkid) {
		DetailsInformation detailsInformation = detailsInformationDao.getAllDetailsInformation(checkid);
		return detailsInformation;
	}
	
	public Rtm001 getRtm001DetailsInformation(String checkid) {
		Rtm001 detailsInformation = detailsInformationDao.getRtm001DetailsInformation(checkid);
		return detailsInformation;
	}
	public List<Rtm00101> getAllInformationByRtm00101(String checkid) {
		List<Rtm00101> detailsInformation = detailsInformationDao.getAllInformationByRtm00101(checkid);
		return detailsInformation;
	}
	public Rtm002 getRtm002DetailsInformation(String checkid) {
		Rtm002 detailsInformation = detailsInformationDao.getRtm002DetailsInformation(checkid);
		return detailsInformation;
	}
	public List<Rtm00201> getAllInformationByRtm00201(String checkid) {
		List<Rtm00201> detailsInformation = detailsInformationDao.getAllInformationByRtm00201(checkid);
		return detailsInformation;
	}
}
