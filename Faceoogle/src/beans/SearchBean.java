package beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;


import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import logic.UserLogic;

@ViewScoped
@ManagedBean(name = "searchBean")
public class SearchBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String searchName;
	private ArrayList<String> result;

	public ArrayList<String> getResult(String searchName) {
		result = UserLogic.getUserNames(searchName);
		return result;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public void setResult(ArrayList<String> result) {
		this.result = result;
	}
	
	public void onSelect(SelectEvent event) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("profile.xhtml?faces-redirect=true" + "&user=" + searchName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}