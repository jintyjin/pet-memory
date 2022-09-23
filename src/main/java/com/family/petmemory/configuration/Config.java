package com.family.petmemory.configuration;

import com.family.petmemory.repository.image.ImageRepository;
import com.family.petmemory.repository.image.JpaImageRepository;
import com.family.petmemory.repository.member.JpaMemberRepository;
import com.family.petmemory.repository.member.MemberRepository;
import com.family.petmemory.repository.pet.JpaPetRepository;
import com.family.petmemory.repository.pet.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
@RequiredArgsConstructor
public class Config {

    private final EntityManager em;

    @Bean
    public MemberRepository memberRepository() {
        return new JpaMemberRepository(em);
    }

    @Bean
    public PetRepository petRepository() {
        return new JpaPetRepository(em);
    }

    @Bean
    public ImageRepository imageRepository() {
        return new JpaImageRepository(em);
    }
}
