/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.20-log : Database - coffeeshop
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`coffeeshop` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `coffeeshop`;

/*Table structure for table `ban` */

DROP TABLE IF EXISTS `ban`;

CREATE TABLE `ban` (
  `MaBan` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `SoBan` int(11) NOT NULL,
  `MaVT` int(10) unsigned NOT NULL,
  `TrangThai` bit(1) DEFAULT NULL,
  PRIMARY KEY (`MaBan`),
  KEY `FK_BAN_VITRI_idx` (`MaVT`),
  CONSTRAINT `FK_BAN_VITRI` FOREIGN KEY (`MaVT`) REFERENCES `vitri` (`MaVT`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `ban` */

insert  into `ban`(`MaBan`,`SoBan`,`MaVT`,`TrangThai`) values (1,1,1,''),(2,2,1,'\0'),(3,3,1,''),(4,4,1,''),(5,5,1,''),(6,6,1,''),(7,7,1,''),(8,8,1,''),(9,9,1,''),(10,10,1,''),(11,11,2,''),(12,12,2,''),(13,13,2,''),(14,14,2,''),(15,15,2,''),(16,16,2,''),(17,17,2,''),(18,18,2,''),(19,19,2,''),(20,20,2,''),(21,21,3,''),(22,22,3,''),(23,23,3,''),(24,24,3,''),(25,25,3,''),(26,26,3,''),(27,27,3,''),(28,28,3,''),(29,29,3,''),(30,30,3,''),(31,31,4,''),(32,32,4,''),(33,33,4,''),(34,34,4,''),(35,35,4,''),(36,36,4,''),(37,37,4,''),(38,38,4,''),(39,39,4,''),(40,40,4,''),(41,41,4,'\0'),(42,42,1,''),(43,43,2,''),(44,44,2,''),(45,45,3,'');

/*Table structure for table `banggia` */

DROP TABLE IF EXISTS `banggia`;

CREATE TABLE `banggia` (
  `MaTU` int(10) unsigned NOT NULL,
  `NgayBan` date NOT NULL,
  `Gia` double unsigned NOT NULL,
  PRIMARY KEY (`MaTU`,`NgayBan`),
  CONSTRAINT `FK_BANGGIA_THUCUONG` FOREIGN KEY (`MaTU`) REFERENCES `thucuong` (`MaTU`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `banggia` */

insert  into `banggia`(`MaTU`,`NgayBan`,`Gia`) values (1,'2017-01-01',20000),(1,'2017-12-10',25000),(2,'2017-01-01',20000),(3,'2017-01-01',18000),(4,'2017-01-01',25000),(5,'2017-01-01',20000),(6,'2017-01-01',25000),(7,'2017-01-01',25000),(8,'2017-01-01',25000),(9,'2017-01-01',25000),(10,'2017-01-01',30000),(11,'2017-01-01',30000),(12,'2017-01-01',30000),(13,'2017-01-01',15000),(14,'2017-01-01',25000),(15,'2017-01-01',28000),(16,'2017-01-01',28000),(17,'2017-01-01',28000),(18,'2017-01-01',28000),(19,'2017-01-01',28000),(20,'2017-01-01',20000),(21,'2017-01-01',25000),(22,'2017-01-01',25000),(23,'2017-01-01',30000),(24,'2017-01-01',20000),(25,'2017-01-01',18000),(26,'2017-01-01',20000),(27,'2017-01-01',20000),(28,'2017-01-01',20000),(29,'2017-01-01',18000),(30,'2017-01-01',20000),(31,'2017-01-01',20000),(32,'2017-01-01',18000),(33,'2017-01-01',18000),(34,'2017-01-01',18000),(35,'2017-01-01',20000),(36,'2017-01-01',25000),(37,'2017-01-01',25000),(38,'2017-01-01',25000),(39,'2017-01-01',25000),(40,'2017-01-01',12000),(41,'2017-01-01',15000),(42,'2017-01-01',15000),(43,'2017-01-01',20000),(44,'2017-01-01',15000),(45,'2017-12-10',15000),(46,'2017-12-10',15000);

/*Table structure for table `chitiethoadon` */

DROP TABLE IF EXISTS `chitiethoadon`;

CREATE TABLE `chitiethoadon` (
  `SoHD` int(10) unsigned NOT NULL,
  `MaTU` int(10) unsigned NOT NULL,
  `SoLuong` int(10) unsigned NOT NULL,
  `NgayLap` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`SoHD`,`MaTU`),
  KEY `FK_CHITIETHOADON_THUCUONG` (`MaTU`),
  CONSTRAINT `FK_CHITIETHOADON_HOADON` FOREIGN KEY (`SoHD`) REFERENCES `hoadon` (`SoHD`),
  CONSTRAINT `FK_CHITIETHOADON_THUCUONG` FOREIGN KEY (`MaTU`) REFERENCES `thucuong` (`MaTU`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `chitiethoadon` */

insert  into `chitiethoadon`(`SoHD`,`MaTU`,`SoLuong`,`NgayLap`) values (1,6,1,'2017-12-15 23:38:29'),(3,8,1,'2017-12-15 23:38:43'),(5,3,2,'2017-12-16 09:23:45'),(5,7,2,'2017-12-16 09:23:39');

/*Table structure for table `chitiethoadonmangve` */

DROP TABLE IF EXISTS `chitiethoadonmangve`;

CREATE TABLE `chitiethoadonmangve` (
  `SoHDMV` int(10) unsigned NOT NULL,
  `MaTU` int(10) unsigned NOT NULL,
  `MaKH` int(10) unsigned NOT NULL,
  `DonGia` double unsigned NOT NULL,
  `SoLuong` double unsigned NOT NULL,
  `NgayLap` date NOT NULL,
  PRIMARY KEY (`SoHDMV`,`MaTU`),
  KEY `FK_CTHDMV_TU` (`MaTU`),
  CONSTRAINT `FK_CTHDMV_HDMV` FOREIGN KEY (`SoHDMV`) REFERENCES `hoadonmangve` (`SoHDMV`),
  CONSTRAINT `FK_CTHDMV_TU` FOREIGN KEY (`MaTU`) REFERENCES `thucuong` (`MaTU`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `chitiethoadonmangve` */

insert  into `chitiethoadonmangve`(`SoHDMV`,`MaTU`,`MaKH`,`DonGia`,`SoLuong`,`NgayLap`) values (1,8,5,25000,1,'2017-10-02'),(1,9,5,25000,1,'2017-10-02'),(2,14,6,25000,1,'2017-10-02'),(2,20,6,20000,1,'2017-10-02'),(3,35,7,20000,1,'2017-10-02'),(4,13,8,15000,1,'2017-10-02'),(4,26,8,20000,1,'2017-10-02'),(4,28,8,20000,1,'2017-10-02'),(5,11,9,30000,2,'2017-10-03'),(6,2,10,20000,2,'2017-10-03'),(6,20,10,20000,2,'2017-10-03'),(7,11,13,30000,2,'2017-10-03'),(8,6,14,25000,1,'2017-10-03'),(8,8,14,25000,1,'2017-10-03'),(9,20,15,20000,1,'2017-10-04'),(10,4,16,25000,1,'2017-10-04'),(11,18,17,28000,1,'2017-10-04'),(12,32,18,18000,2,'2017-10-04'),(13,20,19,20000,1,'2017-10-04'),(13,24,19,20000,1,'2017-10-04'),(14,8,20,25000,1,'2017-10-04'),(14,13,20,15000,2,'2017-10-04'),(15,1,21,20000,1,'2017-10-05'),(15,24,21,20000,2,'2017-10-05'),(16,1,22,20000,2,'2017-10-05'),(16,2,22,20000,2,'2017-10-05'),(17,26,23,20000,1,'2017-10-05'),(17,37,23,35000,1,'2017-10-05'),(18,20,24,20000,2,'2017-10-05'),(19,25,11,20000,1,'2017-10-05'),(19,36,11,25000,1,'2017-10-05'),(20,1,12,20000,1,'2017-10-05');

/*Table structure for table `chitiethopdongcungcap` */

DROP TABLE IF EXISTS `chitiethopdongcungcap`;

CREATE TABLE `chitiethopdongcungcap` (
  `SoHDCC` int(10) unsigned NOT NULL,
  `MaNL` int(10) unsigned NOT NULL,
  `SoLuong` double unsigned NOT NULL,
  `DonGia` double unsigned NOT NULL,
  PRIMARY KEY (`SoHDCC`,`MaNL`),
  KEY `FK_CHITIETHDCC_NL_idx` (`MaNL`),
  CONSTRAINT `FK_CHITIETHDCC_HDCC` FOREIGN KEY (`SoHDCC`) REFERENCES `hopdongcungcap` (`SoHDCC`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_CHITIETHDCC_NL` FOREIGN KEY (`MaNL`) REFERENCES `nguyenlieu` (`MaNL`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `chitiethopdongcungcap` */

insert  into `chitiethopdongcungcap`(`SoHDCC`,`MaNL`,`SoLuong`,`DonGia`) values (1,1,150,12000),(2,3,150,12000),(3,2,150,15000),(4,4,150,10000),(5,5,10,25000),(6,6,50,25000),(7,8,10,30000),(8,9,100,6000),(9,10,100,25000),(10,11,50,35000),(11,13,10,9000),(12,14,10,6000),(13,7,100,9000),(14,15,100,5000),(15,12,150,25000),(16,16,50,20000),(17,2,150,15000),(18,3,150,12000),(19,5,10,25000),(20,15,100,5000),(21,12,150,25000),(22,14,10,6000),(23,13,10,9000),(24,16,50,20000),(25,7,100,9000),(26,8,10,30000);

/*Table structure for table `chonban` */

DROP TABLE IF EXISTS `chonban`;

CREATE TABLE `chonban` (
  `MaKH` int(10) unsigned NOT NULL,
  `NgayGioDen` datetime NOT NULL,
  `MaBan` int(10) unsigned NOT NULL,
  `NgayGioTra` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`MaKH`,`NgayGioDen`),
  KEY `FK_CHONBAN_BAN` (`MaBan`),
  CONSTRAINT `FK_CHONBAN_BAN` FOREIGN KEY (`MaBan`) REFERENCES `ban` (`MaBan`),
  CONSTRAINT `FK_CHONBAN_KHACHHANG` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `chonban` */

insert  into `chonban`(`MaKH`,`NgayGioDen`,`MaBan`,`NgayGioTra`) values (1,'2017-12-15 23:38:40',3,NULL),(2,'2017-12-16 09:23:33',4,NULL),(28,'2017-12-15 23:38:26',1,NULL);

/*Table structure for table `hoadon` */

DROP TABLE IF EXISTS `hoadon`;

CREATE TABLE `hoadon` (
  `SoHD` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `TriGia` double NOT NULL,
  `MaNV` int(10) unsigned NOT NULL,
  `MaKH` int(10) unsigned NOT NULL,
  `ThoiDiem` datetime NOT NULL,
  PRIMARY KEY (`SoHD`),
  KEY `FK_HOADON_CHONBAN` (`MaKH`),
  CONSTRAINT `FK_HOADON_CHONBAN` FOREIGN KEY (`MaKH`) REFERENCES `chonban` (`MaKH`),
  CONSTRAINT `FK_HOADON_NHANVIEN` FOREIGN KEY (`MaKH`) REFERENCES `nhanvien` (`MaNV`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `hoadon` */

insert  into `hoadon`(`SoHD`,`TriGia`,`MaNV`,`MaKH`,`ThoiDiem`) values (1,25000,1,28,'2017-12-15 23:38:29'),(2,0,1,1,'2017-12-15 23:38:40'),(3,25000,1,1,'2017-12-15 23:38:43'),(4,0,1,2,'2017-12-16 09:23:33'),(5,86000,1,2,'2017-12-16 09:23:39');

/*Table structure for table `hoadonmangve` */

DROP TABLE IF EXISTS `hoadonmangve`;

CREATE TABLE `hoadonmangve` (
  `SoHDMV` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `MaKH` int(10) unsigned NOT NULL,
  `TriGia` double unsigned NOT NULL,
  PRIMARY KEY (`SoHDMV`),
  KEY `FK_HOADONMANGVE_KHACHHANG` (`MaKH`),
  CONSTRAINT `FK_HOADONMANGVE_KHACHHANG` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `hoadonmangve` */

insert  into `hoadonmangve`(`SoHDMV`,`MaKH`,`TriGia`) values (1,5,50000),(2,6,45000),(3,7,20000),(4,8,55000),(5,9,60000),(6,10,80000),(7,11,60000),(8,12,50000),(9,13,20000),(10,14,25000),(11,15,28000),(12,16,36000),(13,17,40000),(14,18,55000),(15,19,60000),(16,20,80000),(17,21,55000),(18,22,40000),(19,23,45000),(20,24,20000);

/*Table structure for table `hopdongcungcap` */

DROP TABLE IF EXISTS `hopdongcungcap`;

CREATE TABLE `hopdongcungcap` (
  `SoHDCC` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NgayKyHD` date NOT NULL,
  `NgayThanhLyHD` date NOT NULL,
  `MaNCC` int(10) unsigned NOT NULL,
  PRIMARY KEY (`SoHDCC`),
  KEY `FK_HOPDONGCUNGCAP_NHACUNGCAP` (`MaNCC`),
  CONSTRAINT `FK_HOPDONGCUNGCAP_NHACUNGCAP` FOREIGN KEY (`MaNCC`) REFERENCES `nhacungcap` (`MaNCC`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `hopdongcungcap` */

insert  into `hopdongcungcap`(`SoHDCC`,`NgayKyHD`,`NgayThanhLyHD`,`MaNCC`) values (1,'2017-01-01','2018-01-01',1),(2,'2017-01-01','2019-01-01',2),(3,'2017-05-01','2019-05-01',3),(4,'2017-01-00','2020-01-01',4),(5,'2017-01-01','2020-08-01',5),(6,'2017-05-01','2019-05-01',6),(7,'2017-06-01','2017-01-01',7),(8,'2017-01-01','2019-06-01',8),(9,'2017-01-01','2019-01-01',9),(10,'2017-01-01','2020-05-01',10),(11,'2017-01-01','2020-01-01',11),(12,'2017-01-01','2019-06-01',12),(13,'2017-01-01','2019-01-01',13),(14,'2017-01-01','2020-06-01',14),(15,'2017-01-01','2019-01-01',15),(16,'2017-01-01','2019-06-01',16),(17,'2017-01-01','2020-01-01',17),(18,'2017-01-01','2019-01-01',18),(19,'2017-01-01','2019-01-01',19),(20,'2017-01-01','2018-01-01',20),(21,'2017-01-01','2019-06-01',21),(22,'2017-01-01','2018-01-01',22),(23,'2017-01-01','2019-06-01',23),(24,'2017-01-01','2018-06-01',24),(25,'2017-01-01','2019-06-01',25),(26,'2017-01-01','2018-06-01',26);

/*Table structure for table `khachhang` */

DROP TABLE IF EXISTS `khachhang`;

CREATE TABLE `khachhang` (
  `MaKH` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `HoTenKH` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `GioiTinh` bit(1) DEFAULT NULL,
  `NgaySinh` date DEFAULT NULL,
  `DiaChi` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SoDienThoai` varchar(14) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MaLoaiKH` int(11) unsigned NOT NULL,
  PRIMARY KEY (`MaKH`),
  KEY `FK_KHACHHANG_LOAIKHACHHANG` (`MaLoaiKH`),
  CONSTRAINT `FK_KHACHHANG_LOAIKHACHHANG` FOREIGN KEY (`MaLoaiKH`) REFERENCES `loaikhachhang` (`MaLoaiKH`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `khachhang` */

insert  into `khachhang`(`MaKH`,`HoTenKH`,`GioiTinh`,`NgaySinh`,`DiaChi`,`SoDienThoai`,`MaLoaiKH`) values (1,'Nguyễn Văn Tiến','','1997-08-15','498 Lê Văn Việt, Q.9, TP.HCM','01678945621',1),(2,'Lương Xuân Trường','','1997-02-05','102 Tăng Nhơn Phú,Q.9, TP.HCM','01694687412',1),(3,'Phạm Thị Ngọc Diễm','\0','1997-12-18','128 Phạm Công Trứ, Q.2, TP.HCM','0913489748',2),(4,'Phan Thị Kim Anh','\0','1997-03-26','57 Nguyễn Du, P. Bến Nghé, Q.1, TP.HCM','01247859657',2),(5,'Tô Kim Anh','\0','1997-08-30','12 Nguyễn Thị Định, An Phú, Q.2, TP.HCM','01628794528',3),(6,'Nguyễn Ngọc Hoàn','','1997-05-17','557 Đỗ Xuân Hợp, P. Phước Long B, Q.9,TP.HCM','0909505064',1),(7,'Nguyễn Ngọc Như Tuyế','\0','1998-05-17','47 Kha Vạn Cân,Q.Thủ Đức,TP.HCM','0987453258',3),(8,'Vũ Thị Quỳnh Hương','\0','1995-02-26','18C Phan Văn Trị,P.10,Q. Gò Vấp, TP.HCM','01886795325',2),(9,'Võ Quang Bình','','1994-08-23','599A Lê Đức Thọ,P.14,Q.Gò Vấp, TP.HCM','01639857481',2),(10,'Lưu Anh Quốc','','1992-02-15','25 Võ Văn Ngân,Q. Thủ Đức,TP.HCM','0866987841',3),(11,'Lê Bảo Trâm','\0','1995-02-25','338 Nguyễn Oanh,P.17, Q. Gò Vấp, TP.HCM','01236895465',1),(12,'Đặng Phú Quí','','1992-05-02','35 Đường Số 2, Linh Đông, Thủ Đức, Linh Chiểu Thủ Đức, TP.HCM','01678465121',1),(13,'Bùi Tiến Dũng','','1997-05-03','1023 Kha Vạn Cân, Linh Chiểu, Thủ Đức, TP.HCM','01678465123',2),(14,'Nguyễn Đình Nhật Tài','','1995-06-03','959D, Kha Vạn Cân, Phường Linh Tây, Quận Thủ Đức, TP.HCM','0913157855',3),(15,'Huỳnh Minh Sang','','1995-04-07','1173 Kha Vạn Cân, Linh Chiểu, Thủ Đức, TP.HCM','01687845161',2),(16,'Hồ Quang Minh','','1997-05-06','6 Đào Trinh Nhất, Linh Tây, TP.HCM','01678452314',1),(17,'Dương Quang Cường','','1992-05-03',' 39661 Đường Số 9, Phước Linh Tây, TP.HCM','0913178978',1),(18,'Nguyễn Thành Phát','','1989-06-03','953 Phạm Văn Đồng, Linh Tây, TP.HCM','01678465121',2),(19,'Nguyễn Hoàng Thiên Vũ','','1978-05-03','14/2, HT43, Kp.3, P.Hiệp Thành, Q .12. TP.HCM','01889784612',1),(20,'Nguyễn Thị Hoàng Oanh','\0','1990-06-03','10 Quốc lộ 1K, Đông Hòa, Tx. Dĩ An, Bình Dương','0909145784',3),(21,'Nguyễn Hoàng Kim Long','','1995-04-07','56/8C, Ấp Đông Tác, Phường Tân Đông Hiệp, Thị Xã Dĩ An, Bình An, Tx. Dĩ An, Bình Dương','0913484512',3),(22,'Nguyễn Anh Dũng','','1992-04-25','1017/58/3/19 Đường Lê Văn Lương . Xã Phước Kiển . Nhà Bè ','01678462123',1),(23,'Nguyễn Minh Thương','\0','1994-06-28','1060, Phạm Văn Đồng, Hiệp Bình Chánh, TP.HCM','01678465132',2),(24,'Nguyễn Anh Kiệt','','1996-03-17',' 1162 Phạm Văn Đồng,P. Linh Đông,Q. Thủ Đức, TP.HCM','01668795421',2),(25,'Dương Cao Hưng','','1995-02-28','746 Kha Vạn Cân,Phường Linh Đông ,Quận Thủ Đức,TP.HCM','01626486951',3),(26,'Võ Xuân Thịnh','','1997-02-03','An Bình, Tx. Dĩ An, Bình Dương','0913184955',2),(27,'Phạm Đan Thanh','\0','1995-06-17','Số 1,P. Linh Trung, Q. Thủ Đức, TP.HCM','01648789851',1),(28,'Anonymous','',NULL,NULL,NULL,3);

/*Table structure for table `lichtruc` */

DROP TABLE IF EXISTS `lichtruc`;

CREATE TABLE `lichtruc` (
  `MaNV` int(10) unsigned NOT NULL,
  `ThoiGian` datetime NOT NULL,
  `MaVT` int(11) unsigned NOT NULL,
  `SoGio` double unsigned NOT NULL,
  PRIMARY KEY (`MaNV`,`ThoiGian`),
  KEY `FK_LICHTRUC_VITRI` (`MaVT`),
  CONSTRAINT `FK_LICHTRUC_NHANVIEN` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`),
  CONSTRAINT `FK_LICHTRUC_VITRI` FOREIGN KEY (`MaVT`) REFERENCES `vitri` (`MaVT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `lichtruc` */

insert  into `lichtruc`(`MaNV`,`ThoiGian`,`MaVT`,`SoGio`) values (1,'2017-10-01 07:00:00',1,8),(1,'2017-10-02 07:00:00',3,8),(1,'2017-10-03 07:00:00',2,12),(2,'2017-10-01 13:00:00',1,4),(2,'2017-10-02 13:00:00',3,6),(2,'2017-10-03 07:00:00',4,12),(3,'2017-10-01 07:00:00',1,8),(3,'2017-10-02 08:00:00',4,4),(3,'2017-10-03 07:00:00',2,12),(4,'2017-10-01 13:00:00',1,6),(4,'2017-10-02 18:00:00',2,4),(4,'2017-10-03 07:00:00',2,12),(5,'2017-10-01 08:00:00',1,4),(5,'2017-10-02 07:00:00',3,8),(5,'2017-10-03 07:00:00',4,12),(6,'2017-10-01 18:00:00',4,4),(6,'2017-10-02 16:00:00',3,8),(6,'2017-10-03 12:00:00',2,12),(7,'2017-10-01 12:00:00',3,8),(7,'2017-10-02 07:00:00',4,6),(7,'2017-10-03 07:00:00',3,12),(8,'2017-10-01 12:00:00',3,4),(8,'2017-10-02 12:00:00',2,8),(8,'2017-10-03 07:00:00',2,12),(9,'2017-10-04 07:00:00',1,4),(9,'2017-10-05 12:00:00',2,8),(10,'2017-10-04 07:00:00',1,4),(10,'2017-10-05 12:00:00',2,8),(11,'2017-10-04 07:00:00',1,4),(11,'2017-10-05 07:00:00',3,12),(12,'2017-10-04 12:00:00',4,8),(12,'2017-10-05 07:00:00',3,12),(13,'2017-10-04 12:00:00',4,8),(13,'2017-10-05 07:00:00',3,12),(14,'2017-10-04 12:00:00',4,8),(14,'2017-10-05 07:00:00',3,12),(15,'2017-10-04 18:00:00',1,4),(15,'2017-10-05 18:00:00',3,4),(16,'2017-10-04 12:00:00',4,8),(16,'2017-10-05 18:00:00',2,4),(17,'2017-10-04 18:00:00',3,4),(17,'2017-10-05 12:00:00',2,8),(18,'2017-10-04 07:00:00',1,4),(18,'2017-10-05 12:00:00',2,8),(19,'2017-10-04 07:00:00',1,4),(19,'2017-10-05 18:00:00',2,4),(20,'2017-10-04 18:00:00',2,4),(20,'2017-10-05 12:00:00',1,8);

/*Table structure for table `loaikhachhang` */

DROP TABLE IF EXISTS `loaikhachhang`;

CREATE TABLE `loaikhachhang` (
  `MaLoaiKH` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `TenLoaiKH` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`MaLoaiKH`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `loaikhachhang` */

insert  into `loaikhachhang`(`MaLoaiKH`,`TenLoaiKH`) values (1,'khách VIP'),(2,'thành viên '),(3,'khách lẻ');

/*Table structure for table `loainguyenlieu` */

DROP TABLE IF EXISTS `loainguyenlieu`;

CREATE TABLE `loainguyenlieu` (
  `MaLoaiNV` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `TenLoaiNV` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`MaLoaiNV`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `loainguyenlieu` */

insert  into `loainguyenlieu`(`MaLoaiNV`,`TenLoaiNV`) values (1,'Cafe '),(2,'Đồ uống đá xay'),(3,'Sinh tố Smothies'),(4,'Nước ép trái cây'),(5,'Trà'),(6,'Nguyên liệu khác');

/*Table structure for table `nguyenlieu` */

DROP TABLE IF EXISTS `nguyenlieu`;

CREATE TABLE `nguyenlieu` (
  `MaNL` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `TenNL` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `MaLoaiNV` int(10) unsigned NOT NULL,
  PRIMARY KEY (`MaNL`),
  KEY `FK_NGUYENLIEU_LOAINGUYENLIEU` (`MaLoaiNV`),
  CONSTRAINT `FK_NGUYENLIEU_LOAINGUYENLIEU` FOREIGN KEY (`MaLoaiNV`) REFERENCES `loainguyenlieu` (`MaLoaiNV`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `nguyenlieu` */

insert  into `nguyenlieu`(`MaNL`,`TenNL`,`MaLoaiNV`) values (1,'Hạt cà phê',1),(2,'Bột Onemix',1),(3,'Bột Cacao',1),(4,'Bột Socola',1),(5,'Kem Rich - Base',2),(6,'Mứt hoa quả',3),(7,'Bánh Ritz - Oreo',3),(8,'Các loại hoa quả',3),(9,'Sữa tươi không đường - có đường',3),(10,'Sữa chua không đường - có đường',3),(11,'Các loại trà túi lọc',5),(12,'Siro và mứt đóng hộp',5),(13,'Đường',6),(14,'Đá',6),(15,'Trang trí : Lá bạc hà, hoa quả trang trí ...',6),(16,'Các loại bánh phụ đi kèm',6);

/*Table structure for table `nhacungcap` */

DROP TABLE IF EXISTS `nhacungcap`;

CREATE TABLE `nhacungcap` (
  `MaNCC` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `TenNCC` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `SoDienThoai` varchar(14) COLLATE utf8_unicode_ci NOT NULL,
  `DiaChi` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`MaNCC`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `nhacungcap` */

insert  into `nhacungcap`(`MaNCC`,`TenNCC`,`SoDienThoai`,`DiaChi`) values (1,'Coffee Hoàng Thủy','0908324739','95 Nhật Chi Mai, P.12, Q. Tân Bình, TP.HCM'),(2,'TNT Drink and Coffee','0905560250','320 Lãng Binh Thăng, P.11, Q. 11,TP.HCM'),(3,'Coffee Tree','0904234734','284 Hùng Vương, Thị trấn MADAGUI,Da HUOAI, Lâm Đồng'),(4,'Công Ty TNHH MTV TMDV Cà Phê Việt','02866605898','971 Trần Xuân Soạn, P.Tân Hưng,Q.7,TP.HCM'),(5,'Typical Coffee','0938002088','47/17 Hoàng Xuân Nhị, Q.Tân Phú, TP.HCM'),(6,'Golden Moutain Coffee','0328900659','226 Điện Biên Phủ.P.6,Q.3,TP.HCM'),(7,'Bona Coffee','0909471091','R4-10-11 Hưng Gia 1, Phú Mỹ Hưng,P.Tân Phong,Q.7,TP.HCM'),(8,'Công Ty TNHH Nam Thiên Ân','0286258968','1/6P Nguyễn Thị Sóc, Hưng Lâm, Bà Điểm, Hóc Môn, TP.HCM'),(9,'Công Ty TNHH Cà Phê An Bình','0907160469','Số 20, Đường Số 6. P. Tam Phú, Q. Thủ Đức, Tp.HCM'),(10,'Công Ty TNHH AVC HEMERA','0912 144 889','lầu 3,, An Phú Plaza, 117-119 Lý Chính Thắng, P7, Q3, TP.HCM'),(11,'Công Ty TNHH MTV Sản Xuất Thương Mại Đông Phương','(028) 37610399','Số 2516B Ba Tơ, P. 7, Q. 8,TP.HCM'),(12,'Công Ty Cà Phê Cư Bao','(028) 39483274','317/6E Phan Xích Long, P. 2, Q. Phú Nhuận,TP.HCM'),(13,'Nông Trường Cà Phê Đăk Uy 3','(0260) 3822131','X. Đăk Mar; H. Đăk Hà, Kon Tum'),(14,'Công Ty TNHH Bảo Lộc Real','(0263) 3868866','372/6 Phan Chu Trinh, P. Lộc Tiến, TP. Bảo Lộc, Lâm Đồng'),(15,'Công Ty TNHH Diệu Trần','0983757475','210/46 Đường Tố Hữu, Tổ 20, Phường Lộc Sơn, TP. Bảo Lộc, Lâm Đồng'),(16,'Doanh Nghiệp Tư Nhân Cà Phê Thủy Ty ','(0263) 3870061','Quốc lộ 20, số 143, X. Đinh Lạc, H. Di Linh, Lâm Đồng'),(17,'Công Ty TNHH Real Cafe','(0263) 3557968','193 Trần Quang Khải, Đà Lạt, Lâm Đồng'),(18,'Công Ty TNHH Trà & Cà Phê Thuần Việt Lâm Đồng','(0263) 3634888','158 Thôn 1, X. Đạ Ròn, H. Đơn Dương, Lâm Đồng'),(19,'Công Ty Cà Phê Đức Lập','(0261) 3741185','Quốc Lộ 14, H. Dak Mil, TT. Dak Mil, Đắk Nông'),(20,'Công Ty TNHH Xây Dựng & Thương Mại Tân Phát','(0261) 3683555','Xóm 5, X. Trúc Sơn, H. Cư Jut, Đắk Nông'),(21,'Công Ty TNHH Cầu Vồng','(0254) 3580120','308/5 Bình Giã, P. Nguyễn An Ninh, Tp. Vũng Tàu, Bà Rịa-Vũng Tàu'),(22,'Cà Phê Thiên Hoàng','0932508081','Tổ 14 Đà Sơn, Hòa Khánh Nam, P. Hòa Khánh Nam, Q. Liên Chiểu, Tp. Đà Nẵng'),(23,'Công Ty TNHH MTV Đại Long Huyền Thoại','(0236) 3990112','96 Lê Văn Hiến, Tp. Đà Nẵng'),(24,'Công Ty TNHH Thiên Cần','(0236) 3888161','75 Lý Tự Trọng, P. Thạch Thang, Q. Hải Châu, Tp. Đà Nẵng'),(25,'Cà Phê Thiên Anh','0908693123','77 Lưu Trọng Lư, Tp. Đà Nẵng'),(26,'Công Ty Rang Say Cà Phê Phương Nam','(0236) 3819522','298 Đống Đa, Q. Hải Châu, Tp. Đà Nẵng');

/*Table structure for table `nhanvien` */

DROP TABLE IF EXISTS `nhanvien`;

CREATE TABLE `nhanvien` (
  `MaNV` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `HoTenNV` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `GioiTinh` bit(1) DEFAULT NULL COMMENT '0: Nữ, 1: Name',
  `NgaySinh` date NOT NULL,
  `DiaChi` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `SoDienThoai` varchar(14) COLLATE utf8_unicode_ci NOT NULL,
  `Email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TrangThai` bit(1) DEFAULT NULL COMMENT '0: Đã nghỉ việc,1: Đang làm',
  PRIMARY KEY (`MaNV`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `nhanvien` */

insert  into `nhanvien`(`MaNV`,`HoTenNV`,`GioiTinh`,`NgaySinh`,`DiaChi`,`SoDienThoai`,`Email`,`TrangThai`) values (1,'Đặng Thị Huyền','\0','2000-12-14','46 Đường Số 1, P. Bình An, Q2, TP HCM','Đặng Thị Huyền','huyendangthi@gmail.com',''),(2,'Hà Minh Quân','','1997-06-15','139/2 Đường số 8,P. Linh Xuân, Q.Thủ Đức, TP.HCM','01653391486','quanhm@gmail.com',''),(3,'Nguyễn Hữu Phú','','1999-08-23','89 Đường số 2, P.3, Q. 11, TP.HCM','01689741654','phunh1999@gmail.com',''),(4,'Nguyễn Thị Mỹ Linh','\0','1995-05-18','774 Cách mạng Tháng Tám, P. 5, Q. Tân Bình, TP.HCM','01697892587','mylinh1805@gmail.com',''),(5,'Phan Minh Anh','\0','1998-12-12','48 Đường số 32, P.An Phú, Q.2. TP.HCM','01632429785','minhanhphan@gmail.com',''),(6,'Phạm Mỹ Anh','\0','1997-12-01','157 Lý Chính Thắng, P.7, Q.3, TP.HCM','01886328749','myanh1999@gmail.com',''),(7,'Nguyễn Thị Phương','\0','1998-05-05','663 Hà Huy Giáp,P. Thạnh Xuân, Q.12, TP.HCM','0909563984','phuongnt0505@gmail.com',''),(8,'Nguyễn Văn Phúc','','1997-06-17','350 Quốc lộ 1, An Phú Đông,Q.12, TP.HCM','01678459652','phucnv@gmail.com',''),(9,'Trần Mạnh Quân','','1996-04-15','32 Võ Văn Ngân, Q.Thủ Đức, TP.HCM','0913698789','quan0617@gmail.com','\0'),(10,'Phạm Thị Khả Mi','\0','1997-12-24','219 Nguyễn Thị Định, Bình Trưng Tây, Q.2, TP.HCM','01678923626','khamint@gmail.com',''),(11,'Phan Bảo Thịnh','','1995-02-15','126 Lê Văn Việt, Q.9, TP.HCM','01698748964','bituong1502@gmail.com',''),(12,'Trần Mạnh Tường','','1998-02-03','1124, Lê Văn Lương, Xã Nhân Đức, Huyện Nhà Bè, TP.HCM','01678520020','sushivnct@gmail.com	',''),(13,'Lê Trần Lam Trường','','1998-12-02','393 Xa Lộ Đông Tây, An Phú, TP.HCM','0918652528','ltlt@gmail.com','\0'),(14,'Nguyễn Thế Huy','','1997-03-03','162 Phạm Văn Đồng, Linh Đông, Thủ Đức,TP.HCM	','0982822032','huy1997@gmail.com','\0'),(15,'Đặng Thị Trúc Ly','\0','1995-07-08','26 Đường số 1 Tô Ngọc Vân, Phường Linh Tây, Quận Thủ Đức, Thủ Đức, TP.HCM','01886451200','dttly0807@gmail.com',''),(16,'Lê Thị Minh Liên','\0','1996-05-05','Đường 9, Phường Linh Tây, Quận Thủ Đức,TP.HCM','01697423256','lienle0505@gmail.com','\0'),(17,'Hoàng Mỹ Linh','\0','1994-05-02','798 Xa lộ Hà Nội, Hiệp Phú, TP.HCM','01668499520','linhhoang1994@gmail.com',''),(18,'Nguyễn Thị Quỳnh Như  ','\0','1993-05-02','Số 8, Tăng Nhơn Phú B, Quận 9, TP.HCM','01648751022','nguyennhu98@gmail.com','\0'),(19,'Phạm Thị Mỹ Lệ','\0','1998-05-08','23, Đường M2, Phước Long B, Quận 9,TP.HCM','01687452120','myle1998@gmail.com',''),(20,'Trần Nguyễn Thanh Uyên  ','\0','1999-05-06','Số 10, Đường Tân Trào, phường Tân Phú, quận 7, Tp. HCM, Tân Phú, TP.HCM','01272545215','uyentrannguyen@gmail.com',''),(21,'Võ Chí Công','','1997-05-03','50 Làng Tăng Phú, Tăng Nhơn Phú A, Q.9, TP.HCM','01678415121','congvc@gmail.com',''),(22,'Phan Thị Huỳnh Trân','\0','1999-02-05','141 đường D3, Phước Long B, Q9, TP.HCM','01678456161','tranphan99@gmail.com',''),(23,'Nguyễn Thị Ngọc Yến','\0','1997-05-05','192 Nguyễn Duy Trinh, Bình Trung Tây, Q.2, TP.HCM','01654841651','yennguyencute@gmail.com','\0'),(24,'Bùi Thị Kiều Anh','\0','1996-12-02','311K21 Lương Định Của, Q,2, TP.HCM','01674848787','kieuanhbui@gmail.com',''),(25,'Vi Trình','','1997-02-03','Số 1, Cao Đức Lân, An Phú, Q.2, TP.HCM','0914187451','vitrinh97@gmail.com',''),(26,'Lê Hà Phương','\0','1999-02-03','381A Nam Hòa, Phước Long A,Q.9, TP.HCM','0913587484','haphuongle@gmail.com','\0'),(27,'Lê Thị Lệ','\0','1996-07-08','526, Phạm Văn Đồng, Linh Đông, TP.HCM','01697552045','thile99@gmail.com	',''),(28,'Nguyễn Thị Hồng Loan  ','\0','1997-04-11','23, Đường số 36, Phường Linh Đông, Quận Thủ Đức, TP.HCM','0909738234','loanhong@gmail.com	','\0'),(29,'Võ Thị Lan Anh','\0','1995-12-12','26, Đường số 30, Linh Đông, Thủ Đức, TP.HCM','0907146357  ','anhvo23@gmail.com	',''),(30,'Nguyễn Hồng Nhân','','1999-11-04','1162 Phạm Văn Đồng, Linh Đông, Thủ Đức, TP.HCM','01687451325','nhanhongnguyen@gmail.com','\0'),(31,'Nguyễn Hữu Phước','','1995-08-11','25, Đường số 3, Phường Linh Chiểu, Q. Thủ Đức, Tp.HCM','01678451217','phuocnguyen@gmail.com','\0'),(32,'Phạm Tuân','','2000-12-18','46 Đường số 1, P. Bình An, Q2','Phạm Tuân','tuanpham@gmail.com',''),(33,'Lệ Thương','\0','1989-05-19','1 Phan Đăng Lưu','Lệ Thương','lethuong@gmail.com','');

/*Table structure for table `nhomthucuong` */

DROP TABLE IF EXISTS `nhomthucuong`;

CREATE TABLE `nhomthucuong` (
  `MaNhomTU` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `TenNhomTU` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`MaNhomTU`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `nhomthucuong` */

insert  into `nhomthucuong`(`MaNhomTU`,`TenNhomTU`) values (1,'coffee - sữa - cacao'),(2,'Trà - lipton'),(3,'Sinh tố'),(4,'Yogurt - Kem'),(5,'Nước ép'),(6,'Chanh - Xí muội'),(7,'Matcha'),(8,'Nước ngọt'),(9,'Smothies');

/*Table structure for table `phache` */

DROP TABLE IF EXISTS `phache`;

CREATE TABLE `phache` (
  `MaTU` int(10) unsigned NOT NULL,
  `MaNL` int(10) unsigned NOT NULL,
  PRIMARY KEY (`MaTU`,`MaNL`),
  KEY `FK_PHACHE_NGUYENLIEU` (`MaNL`),
  CONSTRAINT `FK_PHACHE_NGUYENLIEU` FOREIGN KEY (`MaNL`) REFERENCES `nguyenlieu` (`MaNL`),
  CONSTRAINT `FK_PHACHE_THUCUONG` FOREIGN KEY (`MaTU`) REFERENCES `thucuong` (`MaTU`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `phache` */

insert  into `phache`(`MaTU`,`MaNL`) values (1,1),(3,3),(5,3),(14,8),(3,9),(8,10),(8,11),(1,13),(5,13),(14,13),(8,14),(14,14);

/*Table structure for table `phieuchi` */

DROP TABLE IF EXISTS `phieuchi`;

CREATE TABLE `phieuchi` (
  `SoPC` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `SoHDCC` int(10) unsigned NOT NULL,
  `TriGia` double NOT NULL,
  PRIMARY KEY (`SoPC`),
  KEY `FK_PHIEUCHI_HOPDONGCUNGCAP` (`SoHDCC`),
  CONSTRAINT `FK_PHIEUCHI_HOPDONGCUNGCAP` FOREIGN KEY (`SoHDCC`) REFERENCES `hopdongcungcap` (`SoHDCC`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `phieuchi` */

insert  into `phieuchi`(`SoPC`,`SoHDCC`,`TriGia`) values (3,1,3600000),(4,2,3750000),(5,3,250000),(6,4,1550000),(7,5,3100000),(8,6,150000),(9,7,1400000),(10,8,4750000);

/*Table structure for table `thucuong` */

DROP TABLE IF EXISTS `thucuong`;

CREATE TABLE `thucuong` (
  `MaTU` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `TenTU` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `MaNhomTU` int(10) unsigned NOT NULL,
  `DonViTinh` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `TonKho` double NOT NULL,
  `GiaVon` double NOT NULL,
  `GiaBan` double NOT NULL,
  PRIMARY KEY (`MaTU`),
  KEY `FK_THUCUONG_NHOMTHUCUONG` (`MaNhomTU`),
  CONSTRAINT `FK_THUCUONG_NHOMTHUCUONG` FOREIGN KEY (`MaNhomTU`) REFERENCES `nhomthucuong` (`MaNhomTU`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `thucuong` */

insert  into `thucuong`(`MaTU`,`TenTU`,`MaNhomTU`,`DonViTinh`,`TonKho`,`GiaVon`,`GiaBan`) values (1,'Cafe Đen',1,'Ly',20,12000,20000),(2,'Cafe Nâu',1,'Ly',10,15000,20000),(3,'Sữa tươi cafe',1,'Ly',10,10000,18000),(4,'Sữa tươi đá',1,'Ly',0,15000,25000),(5,'Cacao nóng',1,'Ly',0,12000,20000),(6,'Cacao sữa nóng ',1,'Ly',0,15000,25000),(7,'Trà gừng nóng ',2,'Ly',0,15000,25000),(8,'Trà sữa matcha',2,'Ly',10,12000,25000),(9,'Trà Đào',2,'Ly',0,12000,25000),(10,'Lipton đá',2,'Ly',0,20000,30000),(11,'Lipton xí muội',2,'Ly',0,20000,30000),(12,'Lipton Sữa',2,'Ly',0,16000,30000),(13,'Lipton Chanh dây',2,'Ly',0,8000,15000),(14,'Sinh tố Saphoche',3,'Ly',0,12000,25000),(15,'Sinh tố Mãng cầu',3,'Ly',0,15000,28000),(16,'Sinh tố Dâu',3,'Ly',0,15000,28000),(17,'Sinh tố Việt Quất',3,'Ly',0,15000,28000),(18,'Sinh tố Bơ',3,'Ly',0,15000,28000),(19,'Sinh tố Dâu Tằm',3,'Ly',0,15000,28000),(20,'Yagurt đá',4,'Cốc',0,12000,20000),(21,'Yagurt Dâu tằm',4,'Cốc',0,15000,25000),(22,'Yagurt Bơ dằm',4,'Cốc',0,15000,25000),(23,'Yagurt 3 tầng',4,'Ly',0,20000,30000),(24,'Nước ép Cà chua',5,'Ly',0,10000,20000),(25,'Nước ép Chanh dây',5,'Ly',0,10000,18000),(26,'Nước ép Cà rốt',5,'Ly',0,10000,20000),(27,'Nước ép Thơm',5,'Ly',0,10000,20000),(28,'Nước ép Ổi',5,'Ly',0,10000,20000),(29,'Nước ép Cam vắt',5,'Ly',0,10000,18000),(30,'Nước ép Bưởi',5,'Ly',0,10000,20000),(31,'Nước ép không đá',5,'Ly',0,10000,20000),(32,'Chanh đá',6,'Ly',0,9000,18000),(33,'Chanh muối đá',6,'Ly',0,9000,18000),(34,'Xí muội nóng - đá',6,'Ly',0,9000,18000),(35,'Dừa tươi',6,'Trái',50,12000,20000),(36,'Sinh tố Matcha',7,'Ly',0,12000,25000),(37,'Soda Matcha',7,'Ly',0,12000,25000),(38,'Soda Bạc hà',7,'Ly',0,12000,25000),(39,'Latte Greentea',7,'Ly',0,12000,25000),(40,'Nước suối',8,'Chai',150,5000,12000),(41,'Coca Cola',8,'Lon',150,8000,15000),(42,'Sting',8,'Lon',150,8000,15000),(43,'RedBull',8,'Lon',150,10000,20000),(44,'C2',8,'Chai',150,8000,15000),(45,'Pepsi',8,'Lon',150,8000,15000),(46,'7Up',8,'Lon',150,8000,15000);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userName` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `gender` bit(1) DEFAULT NULL,
  `birthday` date NOT NULL,
  `email` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `phoneNumber` varchar(14) COLLATE utf8_unicode_ci NOT NULL,
  `permission` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `users` */

insert  into `users`(`id`,`userName`,`password`,`gender`,`birthday`,`email`,`phoneNumber`,`permission`) values (1,'admin','123456','\0','1988-08-06','admin@gmail.com','0904688849','admin'),(2,'baokhoi','123',NULL,'1997-12-30','baokhoispkt@gmail.com','0934112112','admin'),(3,'thangphamspk','123456','','1999-08-15','admin@gmail.com','08365830','staff'),(4,'ngocanh','123456',NULL,'1999-09-13','(NULL)ngocanh@gmail.com','012345667','staff');

/*Table structure for table `vitri` */

DROP TABLE IF EXISTS `vitri`;

CREATE TABLE `vitri` (
  `MaVT` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `TenVT` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SoBan` int(10) unsigned NOT NULL COMMENT 'Số bàn đang đặt tại vị trí này',
  `ToiDa` int(10) unsigned NOT NULL COMMENT 'Số bàn tối đa đặt tại vị trí này',
  PRIMARY KEY (`MaVT`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `vitri` */

insert  into `vitri`(`MaVT`,`TenVT`,`SoBan`,`ToiDa`) values (1,'Khu A',10,10),(2,'Khu B',10,10),(3,'Khu C',10,10),(4,'Khu D',10,10);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
