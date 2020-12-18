package com.eAppInformation.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eAppInformation.model.Information;

@Service
@Transactional
public class EAppInformationService {

	@Autowired
	Information information;

	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

//	find role of user
	public String findRole(int id) {
		String detail = null;
		try {
			Session s = sessionFactory.openSession();
			s.beginTransaction();
			Query query = s.createQuery("select role from Information where uniqueid=" + id + "");
			detail = (String) query.uniqueResult();

			s.getTransaction().commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detail;
	}

//	find details of user
	public Information findDetails(int id) {
		information = null;
		try {
			Session s = sessionFactory.openSession();
			s.beginTransaction();
			Query query = s.createQuery("from Information where uniqueid=" + id + "");
			information = (Information) query.uniqueResult();
			s.getTransaction().commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return information;
	}

	public void saveDetails(Information informations, String role) {
		try {
			Session s = sessionFactory.openSession();
			s.beginTransaction();
			information.setUniqueid(informations.getUniqueid());
			information.setName(informations.getName());
			information.setRole(informations.getRole());
			if (role == "staff" || role == "principal") {
				Query query = s.createQuery(
						"update Information set bonafide=:y, transfer=:y, bonafidePendingAt=:y, transferPendingAt=:y where uniqueid=:id");
				query.setParameter("y", "Not Required");
				query.setParameter("id", informations.getUniqueid());
				query.executeUpdate();
			}
			s.getTransaction().commit();
			s.close();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

	public Information uniqueIdPresent(int id) {
		information = null;
		try {
			Session s = sessionFactory.openSession();
			s.beginTransaction();

			Query query = s.createQuery("from Information where uniqueid=" + id + "");
			information = (Information) query.uniqueResult();
			s.getTransaction().commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return information;
	}

//	add request
	public Information addCertificate(String certificate) {
		try {
			Session s = sessionFactory.openSession();
			s.beginTransaction();
			String requested = "requested";
			if (certificate == "Bonafide") {
				Query query = s
						.createQuery("update Information set bonafide=:y, bonafidePendingAt=:p where uniqueid=:id");
				query.setParameter("y", requested);
				query.setParameter("p", "Staff");
				query.setParameter("id", information.getUniqueid());
				query.executeUpdate();
			}
			if (certificate == "Transfer") {
				Query query = s.createQuery("update Student set transfer=:y, transferPendingAt=:p where uniqueid=:id");
				query.setParameter("y", requested);
				query.setParameter("p", "Staff");
				query.setParameter("id", information.getUniqueid());
				query.executeUpdate();
			}
			s.getTransaction().commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return information;
	}

//	finding list
	@SuppressWarnings("unchecked")
	public List<Information> findList(String certificate, String pendingAt) {
		List<Information> list = new ArrayList<Information>();
		try {
			Session s = sessionFactory.openSession();
			s.beginTransaction();
			String requested = "requested";

			Query query = null;
			if (certificate == "Bonafide") {
				query = s.createQuery("from Information where bonafide='" + requested + "' and bonafidePendingAt='"
						+ pendingAt + "'");
				list = query.list();
				if (list.isEmpty()) {
					return null;
				}
			}
			if (certificate == "Transfer") {
				query = s.createQuery("from Information where transfer='" + requested + "' and transferPendingAt='"
						+ pendingAt + "'");
				list = query.list();
				if (list.isEmpty()) {
					return null;
				}
			}

			s.getTransaction().commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Information approveRequest(String certificate, int uniqueid) {
		try {
			Session s = sessionFactory.openSession();
			s.beginTransaction();

			if (certificate == "Bonafide") {
				Query query = s
						.createQuery("update Information set bonafidePendingAt=:y where uniqueid=:id and bonafide=:x");
				query.setParameter("y", "Principal");
				query.setParameter("id", uniqueid);
				query.setParameter("x", "requested");
				query.executeUpdate();

			}
			if (certificate == "Transfer") {
				Query query = s
						.createQuery("update Information set transferPendingAt=:y where uniqueid=:id and transfer=:x");
				query.setParameter("y", "Principal");
				query.setParameter("id", uniqueid);
				query.setParameter("x", "requested");
				query.executeUpdate();

			}
			s.getTransaction().commit();
			s.close();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return information;

	}

	public Information rejectRequest(String certificate, int uniqueid) {
		try {
			Session s = sessionFactory.openSession();
			s.beginTransaction();
			if (certificate == "Bonafide") {
				Query query = s
						.createQuery("update Information set bonafide=:y, bonafidePendingAt=:s where uniqueid=:id");
				query.setParameter("y", "rejected");
				query.setParameter("s", null);
				query.setParameter("id", uniqueid);
				query.executeUpdate();

			}
			if (certificate == "Transfer") {
				Query query = s
						.createQuery("update Information set transfer=:y, transferPendingAt=:s where uniqueid=:id");
				query.setParameter("y", "rejected");
				query.setParameter("s", null);
				query.setParameter("id", uniqueid);
				query.executeUpdate();

			}
			s.getTransaction().commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return information;

	}

	public Information acceptedRequest(String certificate, int uniqueid) {
		try {
			Session s = sessionFactory.openSession();
			s.beginTransaction();
			if (certificate == "Bonafide") {
				Query query = s
						.createQuery("update Information set bonafide=:y, bonafidePendingAt=:s where uniqueid=:id");
				query.setParameter("y", "approved");
				query.setParameter("s", null);
				query.setParameter("id", uniqueid);
				query.executeUpdate();

			}
			if (certificate == "Transfer") {
				Query query = s
						.createQuery("update Information set transfer=:y, transferPendingAt=:s where uniqueid=:id");
				query.setParameter("y", "approved");
				query.setParameter("s", null);
				query.setParameter("id", uniqueid);
				query.executeUpdate();

			}
			s.getTransaction().commit();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return information;

	}

}
