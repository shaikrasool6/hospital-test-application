package com.rest.java.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private final Logger log = LoggerFactory.getLogger(HospitalServiceImpl.class);

	@Autowired
	private HospitalDao dao;

	@Override
	public HospitalDto saveHospital(HospitalDto dto) {

		try {
			Hospital hospital = mapDtoToEntity(dto);

			Hospital h = dao.addHospital(hospital);

			if (hospital.getHospId() != null) {

				System.out.println("hosptial added: " + h);

				log.debug("save hospital called");
				return mapEntityToDto(h);

			} else {
				throw new HospitalCustomException();
			}
		} catch (Exception e) {
			log.error("hospial unable to create. A hospital with the name'"+dto.getName()+"' already exists");
			throw new HospitalCustomException(dto.getName());
		}

	}

	@Override
	public HospitalDto updateHospital(HospitalDto dto) {

		Hospital hospital = mapDtoToEntity(dto);

		hospital = dao.updateHospital(hospital);

		if (hospital.getHospId() != null) {

			dto = mapEntityToDto(hospital);

			System.out.println("hospital updated" + hospital);
			log.debug("update hospital called");
			return dto;
		} else {
			log.error("hospital cann't be updated with id= " + hospital.getHospId());
			throw new HospitalCustomException(hospital.getHospId());
		}

	}

	@Override

	public HospitalDto deleteHospital(Integer id) {

		Hospital hospital = dao.deleteHospital(id);
		try {

			if (hospital.getHospId() != null) {

				HospitalDto dto = mapEntityToDto(hospital);

				System.out.println("hospital deleted: " + hospital);
				log.debug("delete hospital called");
				return dto;
			} else {
				throw new HospitalCustomException();
			}
		} catch (Exception e) {
			log.error("hospital id not found for delete= " + id);
			throw new HospitalCustomException(id);
		}

	}

	@Override

	public HospitalDto getHospitalById(Integer id) {

		try {
			Hospital hospital = dao.getOneHospital(id);

			if (hospital.getHospId() != null) {

				HospitalDto dto = mapEntityToDto(hospital);

				System.out.println("hospital by I'd: " + hospital);
				log.debug("getHospitalById called");
				return dto;
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("hospital not found: " + id);
			throw new HospitalCustomException(id);
		}
	}

	@Override
	public List<HospitalDto> getAllHospitals() {

		List<HospitalDto> dtos = new ArrayList<>();

		List<Hospital> hList = dao.getAllHospitals();

		for (int i = 0; i < hList.size(); i++) {

			HospitalDto dto = new HospitalDto();

			BeanUtils.copyProperties(hList.get(i), dto);
			log.debug("ListofAllHosptials called");
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

	

}
