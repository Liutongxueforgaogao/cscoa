package com.hub.entity;
/**
 * 【20170512】：今天exm001主表新加了这些字段，将这部分拿出来，按照checkid来查询
 * 				字段中含有图片信息，有图片需求
 * @author winv87
 *
 */
public class InfromationOfExm001Addition {
		private String checkid;		//systemcode，checkid
		private float feeheji;
		private String taxamt;
		private String taxtype;
		private String isdichong;
		private String affirmtimestamp;
		private String shuilv;
		private float taxrate;
		private String image;
		private String imageTwo;
		private String imageThree;
		private String imageFour;
		private String imageFive;
		private String JHCompanyF;
		private String JHCompanyS;
		public InfromationOfExm001Addition() {
			super();
		}
		
		public String getCheckid() {
			return checkid;
		}
		public void setCheckid(String checkid) {
			this.checkid = checkid;
		}
		
		public String getTaxtype() {
			return taxtype;
		}
		public void setTaxtype(String taxtype) {
			this.taxtype = taxtype;
		}
		public String getIsdichong() {
			return isdichong;
		}
		public void setIsdichong(String isdichong) {
			this.isdichong = isdichong;
		}
		public String getAffirmtimestamp() {
			return affirmtimestamp;
		}
		public void setAffirmtimestamp(String affirmtimestamp) {
			this.affirmtimestamp = affirmtimestamp;
		}
		public String getShuilv() {
			return shuilv;
		}
		public void setShuilv(String shuilv) {
			this.shuilv = shuilv;
		}
		

		public String getTaxamt() {
			return taxamt;
		}

		public void setTaxamt(String taxamt) {
			this.taxamt = taxamt;
		}


		

		public InfromationOfExm001Addition(String checkid, float feeheji, String taxamt, String taxtype,
				String isdichong, String affirmtimestamp, String shuilv, float taxrate, String image, String imageTwo,
				String imageThree, String imageFour, String imageFive, String jHCompanyF, String jHCompanyS) {
			super();
			this.checkid = checkid;
			this.feeheji = feeheji;
			this.taxamt = taxamt;
			this.taxtype = taxtype;
			this.isdichong = isdichong;
			this.affirmtimestamp = affirmtimestamp;
			this.shuilv = shuilv;
			this.taxrate = taxrate;
			this.image = image;
			this.imageTwo = imageTwo;
			this.imageThree = imageThree;
			this.imageFour = imageFour;
			this.imageFive = imageFive;
			JHCompanyF = jHCompanyF;
			JHCompanyS = jHCompanyS;
		}

		public float getFeeheji() {
			return feeheji;
		}

		public void setFeeheji(float feeheji) {
			this.feeheji = feeheji;
		}

		public float getTaxrate() {
			return taxrate;
		}

		public void setTaxrate(float taxrate) {
			this.taxrate = taxrate;
		}

		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
		public String getImageTwo() {
			return imageTwo;
		}
		public void setImageTwo(String imageTwo) {
			this.imageTwo = imageTwo;
		}
		public String getImageThree() {
			return imageThree;
		}
		public void setImageThree(String imageThree) {
			this.imageThree = imageThree;
		}
		public String getImageFour() {
			return imageFour;
		}
		public void setImageFour(String imageFour) {
			this.imageFour = imageFour;
		}
		public String getImageFive() {
			return imageFive;
		}
		public void setImageFive(String imageFive) {
			this.imageFive = imageFive;
		}
		public String getJHCompanyF() {
			return JHCompanyF;
		}
		public void setJHCompanyF(String jHCompanyF) {
			JHCompanyF = jHCompanyF;
		}
		public String getJHCompanyS() {
			return JHCompanyS;
		}
		public void setJHCompanyS(String jHCompanyS) {
			JHCompanyS = jHCompanyS;
		}
		@Override
		public String toString() {
			return "InfromationOfExm001Addition [checkid=" + checkid + ", feeheji=" + feeheji + ", taxamt=" + taxamt
					+ ", taxtype=" + taxtype + ", isdichong=" + isdichong + ", affirmtimestamp=" + affirmtimestamp
					+ ", shuilv=" + shuilv + ", taxrate=" + taxrate + ", image=" + image + ", imageTwo=" + imageTwo
					+ ", imageThree=" + imageThree + ", imageFour=" + imageFour + ", imageFive=" + imageFive
					+ ", JHCompanyF=" + JHCompanyF + ", JHCompanyS=" + JHCompanyS + "]";
		}
		
		
}