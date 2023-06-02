package com.orderManagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.history.Revision;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import com.orderManagement.demo.entity.Demo;
import com.orderManagement.demo.repository.DemoRepository;
import com.orderManagement.model.DemoRespDTO;

@Service
public class DemoService implements IDemoService {
	@Autowired
	DemoRepository demoRepo;

	@PersistenceUnit
	@Qualifier("demoEntityManagerFactory")
	EntityManagerFactory entityManagerFactory;

	@Override
	public List<Demo> getAll() {
		// TODO Auto-generated method stub
		return demoRepo.findAll();
	}

	public Demo saveDemo(Demo demo) {
		// TODO Auto-generated method stub
		return demoRepo.save(demo);
	}

	@Override
	public String delete(Long id) {
		demoRepo.deleteById(id);
		return "item deleted successfully";
	}

	@Override
	public String updateDemo(Long id, Demo demo) {
		try {
			Optional<Demo> demoResp = demoRepo.findById(id);
			if (!demoResp.isEmpty()) {
				demo.setId(demoResp.get().getId());
				BeanUtils.copyProperties(demo, demoResp.get());
				demoRepo.save(demo);
				return "data updated successfully";
			} else {
				return "no item found with id";

			}
		} catch (TransactionSystemException e) {
			System.out.println(e.getMessage());
			return "Please check the mandatory fields";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public List<Revision<Integer, Demo>> getHistory() {
		return demoRepo.findRevisions(2L).getContent();
	}

	@Override
	public List<DemoRespDTO> getHistoryById(Long demoId) {
		List<DemoRespDTO> revs = new ArrayList<DemoRespDTO>();
		List<Revision<Integer, Demo>> rev = demoRepo.findRevisions(demoId).getContent();
		System.out.println(rev);
		rev.forEach(r -> {
//			System.out.println(r.getMetadata().getRevisionNumber());
			DemoRespDTO demoResp = new DemoRespDTO();
			demoResp.setName(r.getEntity().getName());
			demoResp.setScore(r.getEntity().getScore());
			demoResp.setRevId(r.getMetadata().getRevisionNumber().get());
			demoResp.setRevTypeName(r.getMetadata().getRevisionType().toString());
			revs.add(demoResp);
		});
		return revs;
	}

	public Demo getDemoById(Long id) {
		Optional<Demo> demoObj = demoRepo.findById(id);
		if (demoObj.isPresent()) {
			System.out.println(demoObj.get());
		}
		return demoObj.get();
	}

	@Override
	public Demo getDemoByVersion(Long demoId, Long versionId) {
		List<Demo> revs = new ArrayList<Demo>();
		Demo rev = demoRepo.findRevision(demoId, versionId.intValue()).get().getEntity();
		System.out.println(rev);
		return rev;
	}

}
