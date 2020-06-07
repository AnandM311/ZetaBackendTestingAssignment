package com.qa.util;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationsResponse   {

	@JsonProperty("location_suggestions")
	private List<Data> data;

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Data
	{
		
		@JsonProperty("entity_type")
		private String entityType;
		@JsonProperty("entity_id")
		private String entityID;
		private String title;
		@JsonProperty("city_id")
		private String cityID;
		@JsonProperty("city_name")
		private String cityName;
		public String getEntityType() {
			return entityType;
		}
		public void setEntityType(String entityType) {
			this.entityType = entityType;
		}
		public String getEntityID() {
			return entityID;
		}
		public void setEntityID(String entityID) {
			this.entityID = entityID;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getCityID() {
			return cityID;
		}
		public void setCityID(String cityID) {
			this.cityID = cityID;
		}
		public String getCityName() {
			return cityName;
		}
		public void setCityName(String cityName) {
			this.cityName = cityName;
		}
		
		
	}
}
