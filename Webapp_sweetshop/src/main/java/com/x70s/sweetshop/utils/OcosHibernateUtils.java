package com.x70s.sweetshop.utils;

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.x70s.sweetshop.entity.Authority;
import com.x70s.sweetshop.entity.AppUser;
import com.x70s.sweetshop.repository.AuthorityDao;
import com.x70s.sweetshop.repository.AppUserDao;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
public class OcosHibernateUtils {
    @Getter
    private static SessionFactory sessionFactory;

    static {
        sessionFactory = buildSessionFactory();
        loadMasterData();
    }

    private static void loadMasterData() {
        AuthorityDao authorityDao = new AuthorityDao();
        AppUserDao appUserDao = new AppUserDao();

        // Tạo và lưu Authority
        List<Authority> authorities = List.of(
                new Authority("ROLE_ADMIN", "Administrator"),
                new Authority("ROLE_USER", "App User"),
                new Authority("ROLE_SHIPPER", "Shipper"),
                new Authority("ROLE_Staff", "Staff")
        );
        authorityDao.saveAll(authorities);

        // Lấy danh sách Authority từ cơ sở dữ liệu
        List<Authority> savedAuthorities = authorityDao.findAll();

//        // Tạo và lưu Course
//        List<Course> courses = List.of(
//                new Course(null, "Mathematics", "MATH101", 3),
//                new Course(null, "Physics", "PHYS101", 4),
//                new Course(null, "Chemistry", "CHEM101", 3)
//        );
//        courseDao.saveAll(courses);


        // Tạo và lưu AppUser
        List<AppUser> appUsers = new ArrayList<>();
        appUsers.add(AppUser.builder()
                .username("admin")
                .password("123456789") // Nên mã hóa mật khẩu trong thực tế
                .email("admin@x70s.com")
                .phone("+0123456789")
                .authority(savedAuthorities.stream()
                        .filter(a -> a.getCode().equals("ROLE_ADMIN"))
                        .findFirst()
                        .orElse(null)) // Gán một Authority
                .build());

        appUsers.add(AppUser.builder()
                .username("user")
                .password("123456789") // Nên mã hóa mật khẩu trong thực tế
                .email("user@x70s.com")
                .phone("+0123456789")
                .authority(savedAuthorities.stream()
                        .filter(a -> a.getCode().equals("ROLE_ADMIN"))
                        .findFirst()
                        .orElse(null)) // Gán một Authority
                .build());

        appUserDao.saveAll(appUsers);

    }

    private static SessionFactory buildSessionFactory() {
        try {
            URL url = OcosHibernateUtils.class.getClassLoader().getResource("config/sms-hibernate.cfg.xml");
            return new Configuration().configure(url).buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

}
