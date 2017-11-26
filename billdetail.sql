-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 26, 2017 lúc 06:29 AM
-- Phiên bản máy phục vụ: 10.1.25-MariaDB
-- Phiên bản PHP: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `sellphone`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `billdetail`
--

CREATE TABLE `billdetail` (
  `detailid` int(11) NOT NULL,
  `billid` int(11) NOT NULL,
  `productid` int(11) NOT NULL,
  `productname` varchar(200) NOT NULL,
  `price` int(11) NOT NULL,
  `number` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `billdetail`
--

INSERT INTO `billdetail` (`detailid`, `billid`, `productid`, `productname`, `price`, `number`) VALUES
(1, 1, 1, 'Samsung galaxy s8', 20000000, 2),
(2, 6, 15, 'HP 15BS 578TU', 19000000, 1),
(3, 6, 16, 'Iphone 7', 12000000, 1),
(4, 7, 17, 'Iphone 7s Plus', 30000000, 2),
(5, 7, 15, 'HP 15BS 578TU', 57000000, 3);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `billdetail`
--
ALTER TABLE `billdetail`
  ADD PRIMARY KEY (`detailid`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `billdetail`
--
ALTER TABLE `billdetail`
  MODIFY `detailid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
