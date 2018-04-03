package com.myproject.web.login.po;

import java.util.List;

public class AccordionTab {
	private String accordion;
	private List<String> tab;
	private List<AccordionTab> accordionTabList;
	
	public String getAccordion() {
		return accordion;
	}
	public void setAccordion(String accordion) {
		this.accordion = accordion;
	}
	public List<String> getTab() {
		return tab;
	}
	public void setTab(List<String> tab) {
		this.tab = tab;
	}
	public List<AccordionTab> getAccordionTabList() {
		return accordionTabList;
	}
	public void setAccordionTabList(List<AccordionTab> accordionTabList) {
		this.accordionTabList = accordionTabList;
	}
}
