USE [master]
GO
/****** Object:  Database [Social Network]    Script Date: 03/08/2017 20:10:52 ******/
CREATE DATABASE [Social Network] ON  PRIMARY 
( NAME = N'Social Network', FILENAME = N'D:\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\Social Network.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'Social Network_log', FILENAME = N'D:\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\Social Network_log.ldf' , SIZE = 5184KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [Social Network] SET COMPATIBILITY_LEVEL = 100
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Social Network].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Social Network] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [Social Network] SET ANSI_NULLS OFF
GO
ALTER DATABASE [Social Network] SET ANSI_PADDING OFF
GO
ALTER DATABASE [Social Network] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [Social Network] SET ARITHABORT OFF
GO
ALTER DATABASE [Social Network] SET AUTO_CLOSE OFF
GO
ALTER DATABASE [Social Network] SET AUTO_CREATE_STATISTICS ON
GO
ALTER DATABASE [Social Network] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [Social Network] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [Social Network] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [Social Network] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [Social Network] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [Social Network] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [Social Network] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [Social Network] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [Social Network] SET  DISABLE_BROKER
GO
ALTER DATABASE [Social Network] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [Social Network] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [Social Network] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [Social Network] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [Social Network] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [Social Network] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [Social Network] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [Social Network] SET  READ_WRITE
GO
ALTER DATABASE [Social Network] SET RECOVERY SIMPLE
GO
ALTER DATABASE [Social Network] SET  MULTI_USER
GO
ALTER DATABASE [Social Network] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [Social Network] SET DB_CHAINING OFF
GO
USE [Social Network]
GO
/****** Object:  User [Administrator]    Script Date: 03/08/2017 20:10:52 ******/
CREATE USER [Administrator] WITHOUT LOGIN WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  User [Admin]    Script Date: 03/08/2017 20:10:52 ******/
CREATE USER [Admin] FOR LOGIN [Admin] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  Table [dbo].[Reports]    Script Date: 03/08/2017 20:10:53 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Reports](
	[ID] [int] NULL,
	[ID_Post] [int] NULL,
	[Reason] [nvarchar](max) NULL,
	[Date] [nvarchar](max) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Ratings_of_events]    Script Date: 03/08/2017 20:10:53 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Ratings_of_events](
	[ID] [int] NULL,
	[ID_Event] [int] NULL,
	[ID_Author] [int] NULL,
	[Rate] [float] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Posts]    Script Date: 03/08/2017 20:10:53 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Posts](
	[ID] [int] NULL,
	[ID_Author] [int] NULL,
	[ID_Recipient] [int] NULL,
	[Text] [nvarchar](max) NULL,
	[On_my_board] [int] NULL,
	[Date] [nvarchar](max) NULL,
	[Number_of_likes] [int] NULL,
	[Who_likes] [nvarchar](max) NULL,
	[Visible] [nchar](7) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Persons]    Script Date: 03/08/2017 20:10:53 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Persons](
	[ID] [int] NULL,
	[Login] [nchar](36) NULL,
	[Password] [nchar](36) NULL,
	[Email] [nchar](64) NULL,
	[Name] [nchar](64) NULL,
	[Second_name] [nchar](64) NULL,
	[Last_name] [nchar](64) NULL,
	[Date_of_birth] [nchar](10) NULL,
	[Sex] [nchar](6) NULL,
	[Leading_question] [nchar](64) NULL,
	[Answer] [nchar](64) NULL,
	[About_me] [nchar](255) NULL,
	[Friends] [nvarchar](max) NULL,
	[Invited_friends] [nvarchar](max) NULL,
	[Avatar] [image] NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Notes]    Script Date: 03/08/2017 20:10:53 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Notes](
	[ID] [int] NULL,
	[ID_Author] [int] NULL,
	[Contents] [nvarchar](max) NULL,
	[Date] [nvarchar](max) NULL,
	[Title] [nvarchar](max) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Messages]    Script Date: 03/08/2017 20:10:53 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Messages](
	[ID] [int] NULL,
	[ID_Sender] [int] NULL,
	[ID_Recipients] [nvarchar](max) NULL,
	[Saved_Recipients] [nvarchar](max) NULL,
	[Topic] [nvarchar](max) NULL,
	[Contents] [nvarchar](max) NULL,
	[Status] [nvarchar](max) NULL,
	[Previous_status] [nvarchar](max) NULL,
	[Date] [nvarchar](max) NULL,
	[Date_removed] [nvarchar](max) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Logger]    Script Date: 03/08/2017 20:10:53 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Logger](
	[ID] [int] NULL,
	[ID_Author] [int] NULL,
	[Contents] [nvarchar](max) NULL,
	[Date] [nvarchar](max) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Events]    Script Date: 03/08/2017 20:10:53 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Events](
	[ID] [int] NULL,
	[ID_Author] [int] NULL,
	[Title] [nvarchar](max) NULL,
	[Localization] [nvarchar](max) NULL,
	[Description] [nvarchar](max) NULL,
	[Date_start] [nvarchar](max) NULL,
	[Time_start] [nvarchar](max) NULL,
	[Date_finish] [nvarchar](max) NULL,
	[Time_finish] [nvarchar](max) NULL,
	[Rate] [float] NULL,
	[Number_of_ratings] [int] NULL,
	[Who_rating] [nvarchar](max) NULL,
	[Avatar] [image] NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Comments]    Script Date: 03/08/2017 20:10:53 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Comments](
	[ID] [int] NULL,
	[ID_Post] [int] NULL,
	[ID_Author] [int] NULL,
	[Contents] [nvarchar](max) NULL,
	[Date] [nvarchar](max) NULL
) ON [PRIMARY]
GO
