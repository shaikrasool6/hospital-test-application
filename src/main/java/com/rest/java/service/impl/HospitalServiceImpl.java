package com.rest.java.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.java.dao.HospitalDao;
import com.rest.java.dto.HospitalDto;
import com.rest.java.entity.Hospital;
import com.rest.java.exception.HospitalCustomException;
import com.rest.java.service.HospitalService;



/**
 * This is the Service class which can implements HospitalService interface and
 * here taking HospitalDao has-a relation.
 * 
 * @author Shaik
 *
 */

@Service
public class HospitalServiceImpl implements HospitalService {

	@Autowired
	private HospitalDao dao;

	@Override
	public HospitalDto saveHospital(HospitalDto dto) {

		Hospital hospital = mapDtoToEntity(dto);

		Hospital h = dao.addHospital(hospital);
	

		if (hospital.getHospId() != null) {

			System.out.println("hosptial added: " + h);

			return mapEntityToDto(h);

		} else {
			throw new HospitalCustomException();
		}
		

	}

	@Override
	public HospitalDto updateHospital(HospitalDto dto) {

		Hospital hospital = mapDtoToEntity(dto);

		hospital = dao.updateHospital(hospital);

		dto = mapEntityToDto(hospital);

		return dto;
	}

	@Override


	public HospitalDto deleteHospital(Integer id) {


		Hospital hospital = dao.deleteHospital(id);
		try {


		if (hospital.getHospId() != null) {

			HospitalDto dto = mapEntityToDto(hospital);

			System.out.println("hospital deleted: " + hospital);
			
			return dto;
		} else {
			throw new HospitalCustomException();
		}
		}catch (Exception e) {
		
			throw new HospitalCustomException(id);
		}





	}

	@Override


	public HospitalDto getHospitalById(Integer id) {


		Hospital hospital = dao.getOneHospital(id);

		if (hospital.getHospId() != null) {

			HospitalDto dto = mapEntityToDto(hospital);

			System.out.println("hospital by I'd: " + hospital);
			
			return dto;
		} else {
			return null;
		}
	}

	@Override
	public List<HospitalDto> getAllHospitals() {

		List<HospitalDto> dtos = new ArrayList<>();

		List<Hospital> hList = dao.getAllHospitals();

		for (int i = 0; i < hList.size(); i++) {

			HospitalDto dto = new HospitalDto();

			BeanUtils.copyProperties(hList.get(i), dto);

			dtos.add(dto);
		}

		return dtos;

		/*
		 * List<HospitalDto> hospDtosList = null; if (hList != null) { hospDtosList =
		 * new ArrayList<HospitalDto>(); while (((Iterator<Hospital>) hList).hasNext())
		 * { hospDtosList.add(mapEntityToDto(((Iterator<Hospital>) hList).next())); }
		 * 
		 * } else { System.out.println("there is no record...." + hospDtosList); }
		 * 
		 * return hospDtosList;
		 */
	}

	@Override
	public Hospital mapDtoToEntity(HospitalDto dto) {
		Hospital hosp = new Hospital();
		hosp.setHospId(dto.getHospId());
		hosp.setEmail(dto.getEmail());
		hosp.setName(dto.getName());
		hosp.setFax(dto.getFax());
		hosp.setPhone(dto.getPhone());
		return hosp;
	}

	@Override
	public HospitalDto mapEntityToDto(Hospital entity) {
		HospitalDto dto = new HospitalDto();
		dto.setHospId(entity.getHospId());
		dto.setEmail(entity.getEmail());
		dto.setName(entity.getName());
		dto.setFax(entity.getFax());
		dto.setPhone(entity.getPhone());
		return dto;
	}


	/*
	 * @Transactional public Hospital addHospital(HospitalDto hospDto) {
	 * 
	 * return dao.addHospital(hospDto); }
	 * 
	 * @Transactional public Hospital updateHospital(HospitalDto hospDto) { for
	 * (Hospital hosp : hospitals) { if (hosp.getHospId() == hospDto.getHospId()) {
	 * hosp.setName(hospDto.getName()); hosp.setEmail(hospDto.getEmail());
	 * hosp.setPhone(hospDto.getPhone()); hosp.setFax(hospDto.getFax()); return
	 * hosp; } } //return dao.updateHospital(hospDto); return null; }
	 * 
	 * @Transactional public void delteHospotalById(int id) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * @Transactional(readOnly = true) public Hospital findHospialById(int id) { for
	 * (Hospital hosp : hospitals) { if (hosp.getHospId() == id) { return hosp; } }
	 * return null; }
	 * 
	 * //@Transactional public List<Hospital> findAllHospitals() { //List<Hospital>
	 * list = dao.findAllHospitals(); //return list; return null; }
	 * 
	 */


}
