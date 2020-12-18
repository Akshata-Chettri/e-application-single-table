package com.eAppInformation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.eAppInformation.model.Information;
import com.eAppInformation.service.EAppInformationService;

@Controller
public class EAppInformationController {

	@Autowired
	Information information;
	@Autowired
	EAppInformationService eAppInformationService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView userLogin() {
		return new ModelAndView("loginPortal");
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginAuthentication(@ModelAttribute("information") Information info) {
		int id = info.getUniqueid();
		String userRole = eAppInformationService.findRole(id);
		if (null == userRole) {
			return new ModelAndView("loginPortal");
		} else if (userRole.equals("Student")) {
			information = (Information) eAppInformationService.findDetails(id);
			eAppInformationService.saveDetails(information, "student");
			return new ModelAndView("studentPortal", "information", information);
		} else if (userRole.equals("Staff")) {
			information = (Information) eAppInformationService.findDetails(id);
			eAppInformationService.saveDetails(information, "staff");
			return new ModelAndView("staffPortal", "information", information);
		} else if (userRole.equals("Principal")) {
			information = (Information) eAppInformationService.findDetails(id);
			eAppInformationService.saveDetails(information, "principal");
			return new ModelAndView("principalPortal", "information", information);
		} else {
			return new ModelAndView("loginPortal");
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView userLogout() {
		return new ModelAndView("loginPortal");
	}

//								Student Portal
	@RequestMapping(value = "/backToStudentPortal", method = RequestMethod.GET)
	public ModelAndView studentPortal() {
		int id = information.getUniqueid();
		System.out.println(id);
		information = (Information) eAppInformationService.findDetails(id);
		return new ModelAndView("studentPortal", "information", information);
	}

	@RequestMapping(value = "/studentBonafide", method = RequestMethod.GET)
	public ModelAndView studentBonafideRequest() {
		information = (Information) eAppInformationService.addCertificate("Bonafide");
		int id = information.getUniqueid();
		information = (Information) eAppInformationService.findDetails(id);
		return new ModelAndView("studentPortal", "information", information);
	}

	@RequestMapping(value = "/studentTransfer", method = RequestMethod.GET)
	public ModelAndView studentTransferRequest() {
		information = (Information) eAppInformationService.addCertificate("Transfer");
		int id = information.getUniqueid();
		information = (Information) eAppInformationService.findDetails(id);
		return new ModelAndView("studentPortal", "information", information);
	}

	@RequestMapping(value = "/tracker/{uniqueid}", method = RequestMethod.GET)
	public ModelAndView studentTracker(@PathVariable("uniqueid") int uniqueid) {
		information = (Information) eAppInformationService.findDetails(uniqueid);
		return new ModelAndView("tracking", "information", information);
	}

	@RequestMapping(value = "/trackBonafideStatus/{uniqueid}", method = RequestMethod.GET)
	public ModelAndView studentBonafideTracker(@PathVariable("uniqueid") int uniqueid) {
		information = (Information) eAppInformationService.findDetails(uniqueid);
		return new ModelAndView("trackingBonafideStatus", "information", information);
	}

	@RequestMapping(value = "/trackTransferStatus/{uniqueid}", method = RequestMethod.GET)
	public ModelAndView studentTransferTracker(@PathVariable("uniqueid") int uniqueid) {
		information = (Information) eAppInformationService.findDetails(uniqueid);
		return new ModelAndView("trackingTransferStatus", "information", information);
	}

	@RequestMapping(value = "/backToTracking", method = RequestMethod.GET)
	public ModelAndView tracking() {
		information = (Information) eAppInformationService.findDetails(information.getUniqueid());
		return new ModelAndView("tracking", "information", information);
	}

//								Staff Portal
	@RequestMapping(value = "/backToStaffPortal", method = RequestMethod.GET)
	public ModelAndView staffPortal() {
		int id = information.getUniqueid();
		System.out.println(id);
		information = (Information) eAppInformationService.findDetails(id);
		return new ModelAndView("staffPortal", "information", information);
	}

	@RequestMapping(value = "/requestedBonafide", method = RequestMethod.GET)
	public ModelAndView staffBonafideRequest() {
		List<Information> informations = (List<Information>) eAppInformationService.findList("Bonafide", "Staff");
		return new ModelAndView("requestedBonafide", "information", informations);
	}

	@RequestMapping(value = "/requestedTransfer", method = RequestMethod.GET)
	public ModelAndView staffTransferRequest() {
		List<Information> informations = (List<Information>) eAppInformationService.findList("Transfer", "Staff");
		return new ModelAndView("requestedTransfer", "information", informations);
	}

//Bonafide
	@RequestMapping(value = "/approveBonafideStaff/{uniqueid}", method = RequestMethod.GET)
	public ModelAndView approveBonafideStaff(@PathVariable("uniqueid") int id) {
		eAppInformationService.approveRequest("Bonafide", id);
		List<Information> informations = (List<Information>) eAppInformationService.findList("Bonafide", "Staff");
		return new ModelAndView("requestedBonafide", "information", informations);
	}

	@RequestMapping(value = "/rejectBonafideStaff", method = RequestMethod.GET)
	public ModelAndView rejectBonafideStaff(@RequestParam int uniqueid) {
		eAppInformationService.rejectRequest("Bonafide", uniqueid);
		List<Information> informations = (List<Information>) eAppInformationService.findList("Bonafide", "Staff");
		return new ModelAndView("requestedBonafide", "information", informations);
	}

//Transfer
	@RequestMapping(value = "/approveTransferStaff/{uniqueid}", method = RequestMethod.GET)
	public ModelAndView approveTransferStaff(@PathVariable("uniqueid") int id) {
		eAppInformationService.approveRequest("Transfer", id);
		List<Information> informations = (List<Information>) eAppInformationService.findList("Transfer", "Staff");
		return new ModelAndView("requestedTransfer", "information", informations);
	}

	@RequestMapping(value = "/rejectTransferStaff", method = RequestMethod.GET)
	public ModelAndView rejectTransferStaff(@RequestParam int uniqueid) {
		eAppInformationService.rejectRequest("Transfer", uniqueid);
		List<Information> informations = (List<Information>) eAppInformationService.findList("Transfer", "Staff");
		return new ModelAndView("requestedTransfer", "information", informations);
	}

//									Principal Portal
	@RequestMapping(value = "/backToPrincipalPortal", method = RequestMethod.GET)
	public ModelAndView principalPortal() {
		int id = information.getUniqueid();
		information = (Information) eAppInformationService.findDetails(id);
		return new ModelAndView("principalPortal", "information", information);
	}

	@RequestMapping(value = "/approvedBonafide", method = RequestMethod.GET)
	public ModelAndView approvedBonafideRequest() {
		List<Information> informations = (List<Information>) eAppInformationService.findList("Bonafide", "Principal");
		return new ModelAndView("approvedBonafide", "information", informations);
	}

	@RequestMapping(value = "/approvedTransfer", method = RequestMethod.GET)
	public ModelAndView approvedTransferRequest() {
		List<Information> informations = (List<Information>) eAppInformationService.findList("Transfer", "Principal");
		return new ModelAndView("approvedTransfer", "information", informations);
	}

	@RequestMapping(value = "/approvedBonafideRequest/{uniqueid}", method = RequestMethod.GET)
	public ModelAndView approvedBonafide(@PathVariable("uniqueid") int id) {
		eAppInformationService.acceptedRequest("Bonafide", id);
		List<Information> informations = (List<Information>) eAppInformationService.findList("Bonafide", "Principal");
		return new ModelAndView("approvedTransfer", "information", informations);
	}

	@RequestMapping(value = "/approvedTransferRequest/{uniqueid}", method = RequestMethod.GET)
	public ModelAndView approvedTransfer(@PathVariable("uniqueid") int id) {
		eAppInformationService.acceptedRequest("Transfer", id);
		List<Information> informations = (List<Information>) eAppInformationService.findList("Transfer", "Principal");
		return new ModelAndView("approvedTransfer", "information", informations);
	}
}
