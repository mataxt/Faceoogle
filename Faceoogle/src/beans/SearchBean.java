package beans;

import java.io.Serializable;
import java.util.ArrayList;


import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;

import logic.UserLogic;

@ViewScoped
@ManagedBean(name = "searchBean")
public class SearchBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String searchName;

	public ArrayList<String> getNames() {
		return UserLogic.getUserNames(searchName);
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
}