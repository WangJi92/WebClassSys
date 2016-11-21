
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dic_classfiytype` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `dic_fatherstate` int(11) DEFAULT NULL,
  `dic_fixed` int(11) DEFAULT NULL,
  `dic_indexcode` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `dic_name` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `dic_value` int(11) DEFAULT NULL COMMENT '数值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


##申请教室表
DROP TABLE IF EXISTS `applyclassroom`;
CREATE TABLE `applyclassroom` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ac_applicant` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ac_applyIndexCode` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ac_applyReason` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ac_classRoomIndexCode` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ac_classRoomName` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ac_createTime` datetime DEFAULT NULL,
  `ac_handleAddvice` varchar(625) COLLATE utf8_bin DEFAULT NULL,
  `ac_phone` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `ac_purpose` int(11) DEFAULT NULL,
  `ac_state` int(11) DEFAULT NULL,
  `ac_whichLesson` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ac_timetableId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


#申请维修表
DROP TABLE IF EXISTS `applymaintain`;
CREATE TABLE `applymaintain` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `am_adminAddrice` longtext COLLATE utf8_bin,
  `am_applyName` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `am_applyPhone` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `am_ClassRoomName` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `am_applyDetail` longtext COLLATE utf8_bin,
  `am_applyIndexCode` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `am_ClassRoomIndexCode` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `am_createTime` datetime DEFAULT NULL,
  `am_maintainIndexCode` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `am_maintainPeople` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `am_maintainPeoplePhone` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `am_state` int(11) DEFAULT NULL,
  `am_type` int(11) DEFAULT NULL,
  `am_uergencystate` int(11) DEFAULT NULL,
  `am_message` longtext COLLATE utf8_bin,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

#教学楼信息表
DROP TABLE IF EXISTS `buidinginfo`;
CREATE TABLE `buidinginfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `build_buildingtype` int(11) DEFAULT NULL,
  `build_dutyroompeopleindexcode` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `build_floorTotal` int(11) DEFAULT NULL,
  `build_indexcode` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `build_introduceInfo` longtext COLLATE utf8_bin,
  `build_maintancepeopleindexcode` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `build_name` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `build_pictures` longtext COLLATE utf8_bin,
  `build_res1` longtext COLLATE utf8_bin,
  `build_res2` longtext COLLATE utf8_bin,
  `build_res3` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

#教室信息表
DROP TABLE IF EXISTS `classroom`;
CREATE TABLE `classroom` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cm_buildingIndexcode` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `cm_floorNo` int(11) DEFAULT NULL,
  `cm_indexcode` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `cm_introduction` longtext COLLATE utf8_bin,
  `cm_name` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `cm_pictures` longtext COLLATE utf8_bin,
  `cm_res1` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `cm_res2` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `cm_res3` int(11) DEFAULT NULL,
  `cm_seatno` int(11) DEFAULT NULL,
  `cm_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

#设备信息表
DROP TABLE IF EXISTS `equipment`;
CREATE TABLE `equipment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eq_brandname` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `eq_indexcode` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `eq_introduce` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `eq_pictures` longtext COLLATE utf8_bin,
  `eq_type` int(11) DEFAULT NULL,
  `eq_name` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

#课程信息表
DROP TABLE IF EXISTS `timetable`;
CREATE TABLE `timetable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tt_classroomindexcode` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `tt_type` int(11) DEFAULT NULL,
  `tt_whichlesson` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `tt_whichweek` int(11) DEFAULT NULL,
  `tt_whichday` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11901 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

#用户信息表
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_indexcode` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `user_password` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `user_name` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  `user_phone` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `user_picture` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `user_tell` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `user_loginAccount` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

#教室设备信息表
DROP TABLE IF EXISTS `classroomequipment`;
CREATE TABLE `classroomequipment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ce_crindexcode` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ce_eqindexcode` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

