# 数据字典表信息
CREATE TABLE `dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dic_classfiytype` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `dic_fatherstate` int(11) DEFAULT NULL,
  `dic_fixed` int(11) DEFAULT NULL,
  `dic_indexcode` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `dic_name` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `dic_value` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

