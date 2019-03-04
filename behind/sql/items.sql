/*
Navicat MySQL Data Transfer

Source Server         : GraduationProject
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : csom

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-03-04 16:16:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for items
-- ----------------------------
DROP TABLE IF EXISTS `items`;
CREATE TABLE `items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `price` int(11) NOT NULL,
  `discount` int(11) NOT NULL,
  `saled` int(11) NOT NULL,
  `color` varchar(50) NOT NULL DEFAULT '',
  `size` varchar(50) NOT NULL DEFAULT '',
  `imgPath` varchar(20) NOT NULL,
  `location` varchar(10) NOT NULL,
  `description` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of items
-- ----------------------------
INSERT INTO `items` VALUES ('1', '1', '西服套装男士', '500', '380', '0', 'black;red;grey', '160;170;175;180', 'clothes/1.png', '成都', '');
INSERT INTO `items` VALUES ('2', '1', '女生长袖T恤', '499', '400', '0', 'black;red', '150;155;160;170', 'clothes/2.png', '上海', '');
INSERT INTO `items` VALUES ('3', '1', '半袖硬汉上衣', '360', '300', '0', 'green;gold;lemon', '180;190', 'clothes/3.png', '北京', '');
INSERT INTO `items` VALUES ('4', '1', '休闲男士夹克', '788', '560', '0', 'blue;red;white', '180;190;195', 'clothes/4.png', '青岛', '');
INSERT INTO `items` VALUES ('5', '1', '李宁男士西装', '999', '888', '0', 'orange;purple;pink;white', '150;165;180', 'clothes/5.png', '杭州', '');
INSERT INTO `items` VALUES ('6', '1', '李宁男士运动休闲服', '666', '555', '0', 'gray;ivory;white', '155;165;180', 'clothes/6.png', '武汉', '');
INSERT INTO `items` VALUES ('7', '1', '长袖小香风连衣裙', '555', '423', '0', 'white;green;red', '155;160;165;170;175', 'clothes/7.png', '重庆', '');
INSERT INTO `items` VALUES ('8', '1', '花花公子男士长袖衬衫格', '777', '666', '0', 'red;blue;green;white', '170;175', 'clothes/8.png', '青岛', '');
INSERT INTO `items` VALUES ('9', '1', '休闲帅气ins潮牌衣服', '555', '333', '0', 'white;black', '175;180', 'clothes/9.png', '哈尔滨', '');
INSERT INTO `items` VALUES ('10', '1', '春季开衫卫衣男连帽', '333', '222', '0', 'white;red', '170;175;180', 'clothes/10.png', '长春', '');
INSERT INTO `items` VALUES ('11', '1', 'POLO衫百搭修身衣服', '666', '555', '0', 'white', '175', 'clothes/11.png', '沈阳', '');
INSERT INTO `items` VALUES ('12', '2', '三只松鼠坚果', '123', '66', '0', '', '', 'food/1.png', '上海', '源自于大自然，味道纯真');
INSERT INTO `items` VALUES ('13', '2', '农夫鸡蛋', '66', '55', '0', '', '', 'food/2.png', '成都', '最清纯的鸡蛋');
INSERT INTO `items` VALUES ('14', '2', '安徽特产黄山烧饼', '55', '22', '0', '', '', 'food/3.png', '安徽', '梅干菜扣肉酥饼网红美食糕点心零食小吃');
INSERT INTO `items` VALUES ('15', '2', '三只松鼠蒸蛋糕', '159', '89', '0', '', '', 'food/4.png', '北京', '早餐营养零食小面包点心美食代餐');
INSERT INTO `items` VALUES ('16', '2', '百草味凤梨酥', '33', '22', '0', '', '', 'food/5.png', '深圳', '特产零食糕点 美食小吃点心盒装');
INSERT INTO `items` VALUES ('17', '2', '友臣肉松饼', '36', '21', '0', '', '', 'food/6.png', '长沙', '休闲小吃糕点特产美食营养早餐食品网红零食面包');
INSERT INTO `items` VALUES ('18', '2', '盼盼大礼包零食', '89', '64', '0', '', '', 'food/7.png', '陕西', '送礼休闲办公室下午茶小吃糕点组合3050g美食礼包');
INSERT INTO `items` VALUES ('19', '2', '知味观桂花糕', '99', '77', '0', '', '', 'food/8.png', '杭州', '杭州传统特产糕点好吃的茶点心零食美食吃货小吃');
INSERT INTO `items` VALUES ('20', '2', '南国食品海南特产年货', '123', '89', '0', '', '', 'food/9.png', '海南', '原味椰子饭538g方便米饭速食特色小吃美食');
INSERT INTO `items` VALUES ('21', '2', '五芳斋绿豆糕', '86', '56', '0', '', '', 'food/10.png', '沈阳', '伴手礼桂花糕办公室点心休闲小吃零食绿豆饼好吃美食');
INSERT INTO `items` VALUES ('22', '2', 'abd爱情面包', '65', '32', '0', '', '', 'food/11.png', '昆明', '1000g手撕面包整箱营养早餐食品糕点美食零食批发');
INSERT INTO `items` VALUES ('23', '3', '你的善良必须有点锋芒', '99', '66', '0', '', '', 'book/1.png', '北京', '生活不是用来妥协的 明白请趁早 青春励志散文全集');
INSERT INTO `items` VALUES ('24', '3', '思想的星空', '101', '69', '0', '', '', 'book/2.png', '上海', '周国平散文精选风中的纸屑安静善良丰富高贵图书');
INSERT INTO `items` VALUES ('25', '3', '鬼谷子', '155', '102', '0', '', '', 'book/3.png', '成都', '原著珍藏版全书绝学白话文鬼谷子教你攻心术鬼谷子的局心计谋略方与圆人性的弱点厚黑学为人处世智慧鬼谷子书');
INSERT INTO `items` VALUES ('26', '3', '人性的弱点', '139', '100', '0', '', '', 'book/4.png', '重庆', '人生哲理认知自己提高自身修养青春文学 励志成功书籍 人际交往与沟通技巧 成人畅销书');
INSERT INTO `items` VALUES ('27', '3', '羊皮卷全书', '123', '99', '0', '', '', 'book/5.png', '广州', '超值珍藏版 励志书 经典典藏版');
INSERT INTO `items` VALUES ('28', '3', '墨菲定律', '99', '56', '0', '', '', 'book/6.png', '西安', '墨菲定律正版 职场谈判人际交往心理学入门基础书籍 心理学与生活 心理学书籍读心术书籍畅销书排行榜');
INSERT INTO `items` VALUES ('29', '3', '厚黑学', '56', '33', '0', '', '', 'book/7.png', '广西', '青春励志说话办事职场经商人际关系正能量智慧学文学成功励志书籍畅销书排行榜 李宗吾原著');
INSERT INTO `items` VALUES ('30', '3', '老舍散文', '66', '38', '0', '', '', 'book/8.png', '天津', '国学大师散文，百读不厌');
INSERT INTO `items` VALUES ('31', '3', '西游记', '67', '39', '0', '', '', 'book/9.png', '河北', '吴承恩 著作 世界名著文学');
INSERT INTO `items` VALUES ('32', '3', '三国演义', '69', '41', '0', '', '', 'book/10.png', '新疆', '正版青少年版初高中生成人精装原著正版无删减完整全集罗贯中华人民书局现代白话文言文学出版社11');
INSERT INTO `items` VALUES ('33', '3', '水浒传', '72', '45', '0', '', '', 'book/11.png', '西藏', ' 施耐庵著人民文学出版社原著原版四大名著水浒传青少版学生版世界名著中国古典小说书籍');
INSERT INTO `items` VALUES ('34', '4', '阿呆低调裤', '56', '39', '0', 'black;white', '170;175;180;185;190', 'trousers/1.png', '成都', '');
INSERT INTO `items` VALUES ('35', '4', '灯芯绒长裤子', '49', '32', '0', 'grey;black', '170;180', 'trousers/2.png', '昆明', '');
INSERT INTO `items` VALUES ('36', '4', '六六公子休闲裤', '69', '59', '0', 'white;black', '170', 'trousers/3.png', '义乌', '');
INSERT INTO `items` VALUES ('37', '4', '男士九分破洞牛仔裤', '99', '88', '0', 'wathet;blue', '175', 'trousers/4.png', '吉林', '');
INSERT INTO `items` VALUES ('38', '4', '春季男士宽松直筒牛仔裤', '101', '86', '0', 'black', '165;170;175;180', 'trousers/5.png', '上海', '');
INSERT INTO `items` VALUES ('39', '4', '杰雷诺牛仔裤', '123', '99', '0', 'black;white', '165;170;175', 'trousers/6.png', '北京', '');
INSERT INTO `items` VALUES ('40', '4', '微喇叭裤子女2019春秋新款', '99', '88', '0', 'black;blue;grey;green', '150;165;180', 'trousers/7.png', '常德', '');
INSERT INTO `items` VALUES ('41', '4', '唐狮春2019新款高腰白色牛仔裤', '89', '77', '0', 'blue;red;black', '150;160;170;175', 'trousers/8.png', '重庆', '');
INSERT INTO `items` VALUES ('42', '4', '黑色牛仔裤女九分高腰', '66', '55', '0', 'black', '150;165;170', 'trousers/9.png', '南京', '');
INSERT INTO `items` VALUES ('43', '4', '2019春装新款格子哈伦裤女秋冬九分裤', '89', '65', '0', 'brown;white', '165;170;175', 'trousers/10.png', '海南', '');
INSERT INTO `items` VALUES ('44', '4', '怪味少女裤子破洞直筒牛仔裤', '66', '43', '0', 'white;black', '155;160;165', 'trousers/11.png', '上海', '');
