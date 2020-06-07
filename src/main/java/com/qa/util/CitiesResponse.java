package com.qa.util;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CitiesResponse {

	@JsonProperty("location_suggestions")
	private List<Data> data;

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Data {
		private String id;
		private String name;
		@JsonProperty("country_id")
		private String countryId;
		@JsonProperty("country_name")
		private String countryName;
		@JsonProperty("country_flag_url")
		private String countryFlagUrl;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCountryId() {
			return countryId;
		}

		public void setCountryId(String countryId) {
			this.countryId = countryId;
		}

		public String getCountryName() {
			return countryName;
		}

		public void setCountryName(String countryName) {
			this.countryName = countryName;
		}

		public String getCountryFlagUrl() {
			return countryFlagUrl;
		}

		public void setCountryFlagUrl(String countryFlagUrl) {
			this.countryFlagUrl = countryFlagUrl;
		}
	}

}
