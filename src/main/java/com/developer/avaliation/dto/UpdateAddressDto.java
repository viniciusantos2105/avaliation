package com.developer.avaliation.dto;

public class UpdateAddressDto {

    private Long idPerson;

    private Long idAddress;

    public UpdateAddressDto(){}

    public UpdateAddressDto(Long idPerson, Long idAddress) {
        this.idPerson = idPerson;
        this.idAddress = idAddress;
    }

    public Long getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Long idPerson) {
        this.idPerson = idPerson;
    }

    public Long getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(Long idAddress) {
        this.idAddress = idAddress;
    }
}
