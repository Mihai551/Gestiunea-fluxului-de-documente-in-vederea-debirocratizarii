package com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.services;

import java.util.List;

import com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.SimplePackage;

public class OtherServices {
	
	public static boolean containsPackageName(final List<SimplePackage> list, final String name){
	    return list.stream().filter(o -> o.getPackageName().equals(name)).findFirst().isPresent();
	}

}
