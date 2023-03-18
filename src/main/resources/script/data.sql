-- CLEAR TABLE --
drop table third_party_chart_of_account;
drop table chart_of_account;
drop table society_account;
drop table equivalent_currency_detail;
drop table society_equivalent_currency;
drop table society;
drop table account;
drop table currency;


-- Data test --
insert into currency values
('CUR01', 'AR'),
('CUR02', 'EUR'),
('CUR03', 'USD');


insert into account values
('ACC0001', 'admin@gmail.com', '123');


insert into society values
('SOC0001', 'DIMPEX', 'Tovo', 'Production articles industriels et vente de marchandises', 'ENCEINTE ITU ANDOHARANOFOTSY BP 1960 Antananarivo 101', 'Antananarivo', '2023-01-01', NULL, NULL, NULL, NULL, '2023-01-01', 'CUR01');


insert into society_equivalent_currency values
('EQC001', 'SOC0001');


insert into equivalent_currency_detail values
('EQD0001', 'EQC001', 'CUR02', 4500.),
('EQD0002', 'EQC001', 'CUR03', 3900.);


insert into society_account values
('SAC00001', 'ACC0001', 'SOC0001', 'society');


insert into chart_of_account values
('COA00001', '10100', 'SOC0001', 'CAPITAL'),
('COA00002', '10100', 'SOC0001', 'CAPITAL'),
('COA00003', '10100', 'SOC0001', 'CAPITAL'),
('COA00004', '10100', 'SOC0001', 'CAPITAL'),
('COA00005', '10100', 'SOC0001', 'CAPITAL'),
('COA00006', '10100', 'SOC0001', 'CAPITAL'),
('COA00007', '10100', 'SOC0001', 'CAPITAL'),
('COA00008', '10100', 'SOC0001', 'CAPITAL'),
('COA00009', '10100', 'SOC0001', 'CAPITAL'),
('COA00010', '10100', 'SOC0001', 'CAPITAL'),
('COA00011', '10100', 'SOC0001', 'CAPITAL'),
('COA00012', '10100', 'SOC0001', 'CAPITAL'),
('COA00013', '10100', 'SOC0001', 'CAPITAL'),
('COA00014', '10100', 'SOC0001', 'CAPITAL'),
('COA00015', '10100', 'SOC0001', 'CAPITAL'),
('COA00016', '10100', 'SOC0001', 'CAPITAL'),
('COA00017', '10100', 'SOC0001', 'CAPITAL'),
('COA00018', '10100', 'SOC0001', 'CAPITAL'),
('COA00019', '10100', 'SOC0001', 'CAPITAL'),
('COA00020', '10100', 'SOC0001', 'CAPITAL'),
('COA00021', '10100', 'SOC0001', 'CAPITAL'),
('COA00022', '10100', 'SOC0001', 'CAPITAL'),
('COA00023', '10100', 'SOC0001', 'CAPITAL'),
('COA00024', '10100', 'SOC0001', 'CAPITAL'),
('COA00025', '10100', 'SOC0001', 'CAPITAL'),
('COA00026', '10100', 'SOC0001', 'CAPITAL'),
('COA00027', '10100', 'SOC0001', 'CAPITAL'),
('COA00028', '10100', 'SOC0001', 'CAPITAL'),
('COA00029', '10100', 'SOC0001', 'CAPITAL'),
('COA00030', '10100', 'SOC0001', 'CAPITAL'),
('COA00031', '10100', 'SOC0001', 'CAPITAL'),
('COA00032', '10100', 'SOC0001', 'CAPITAL'),
('COA00033', '10100', 'SOC0001', 'CAPITAL'),
('COA00034', '10100', 'SOC0001', 'CAPITAL'),
('COA00035', '10100', 'SOC0001', 'CAPITAL'),
('COA00036', '10100', 'SOC0001', 'CAPITAL'),
('COA00037', '10100', 'SOC0001', 'CAPITAL'),
('COA00038', '10100', 'SOC0001', 'CAPITAL'),
('COA00039', '10100', 'SOC0001', 'CAPITAL'),
('COA00040', '10100', 'SOC0001', 'CAPITAL'),
('COA00041', '10100', 'SOC0001', 'CAPITAL'),
('COA00042', '10100', 'SOC0001', 'CAPITAL'),
('COA00043', '10100', 'SOC0001', 'CAPITAL'),
('COA00044', '10100', 'SOC0001', 'CAPITAL'),
('COA00045', '10100', 'SOC0001', 'CAPITAL'),
('COA00046', '10100', 'SOC0001', 'CAPITAL'),
('COA00047', '10100', 'SOC0001', 'CAPITAL'),
('COA00048', '10100', 'SOC0001', 'CAPITAL'),
('COA00049', '10100', 'SOC0001', 'CAPITAL'),
('COA00050', '10100', 'SOC0001', 'CAPITAL'),
('COA00051', '10100', 'SOC0001', 'CAPITAL'),
('COA00052', '10100', 'SOC0001', 'CAPITAL'),
('COA00053', '10100', 'SOC0001', 'CAPITAL'),
('COA00054', '10100', 'SOC0001', 'CAPITAL'),
('COA00055', '10100', 'SOC0001', 'CAPITAL'),
('COA00056', '10100', 'SOC0001', 'CAPITAL'),
('COA00057', '10100', 'SOC0001', 'CAPITAL'),
('COA00058', '10100', 'SOC0001', 'CAPITAL'),
('COA00059', '10100', 'SOC0001', 'CAPITAL'),
('COA00060', '10100', 'SOC0001', 'CAPITAL'),
('COA00061', '10100', 'SOC0001', 'CAPITAL'),
('COA00062', '10100', 'SOC0001', 'CAPITAL'),
('COA00063', '10100', 'SOC0001', 'CAPITAL'),
('COA00064', '10100', 'SOC0001', 'CAPITAL'),
('COA00065', '10100', 'SOC0001', 'CAPITAL'),
('COA00066', '10100', 'SOC0001', 'CAPITAL'),
('COA00067', '10100', 'SOC0001', 'CAPITAL'),
('COA00068', '10100', 'SOC0001', 'CAPITAL'),
('COA00069', '10100', 'SOC0001', 'CAPITAL'),
('COA00070', '10100', 'SOC0001', 'CAPITAL'),
('COA00071', '10100', 'SOC0001', 'CAPITAL'),
('COA00072', '10100', 'SOC0001', 'CAPITAL'),
('COA00073', '10100', 'SOC0001', 'CAPITAL'),
('COA00074', '10100', 'SOC0001', 'CAPITAL'),
('COA00075', '10100', 'SOC0001', 'CAPITAL'),
('COA00076', '10100', 'SOC0001', 'CAPITAL'),
('COA00077', '10100', 'SOC0001', 'CAPITAL'),
('COA00078', '10100', 'SOC0001', 'CAPITAL'),
('COA00079', '10100', 'SOC0001', 'CAPITAL'),
('COA00080', '10100', 'SOC0001', 'CAPITAL'),
('COA00081', '10100', 'SOC0001', 'CAPITAL'),
('COA00082', '10100', 'SOC0001', 'CAPITAL'),
('COA00083', '10100', 'SOC0001', 'CAPITAL'),
('COA00084', '10100', 'SOC0001', 'CAPITAL'),
('COA00085', '10100', 'SOC0001', 'CAPITAL'),
('COA00086', '10100', 'SOC0001', 'CAPITAL'),
('COA00087', '10100', 'SOC0001', 'CAPITAL'),
('COA00088', '10100', 'SOC0001', 'CAPITAL'),
('COA00089', '10100', 'SOC0001', 'CAPITAL'),
('COA00090', '10100', 'SOC0001', 'CAPITAL'),
('COA00091', '10100', 'SOC0001', 'CAPITAL'),
('COA00092', '10100', 'SOC0001', 'CAPITAL'),
('COA00093', '10100', 'SOC0001', 'CAPITAL'),
('COA00094', '10100', 'SOC0001', 'CAPITAL'),
('COA00095', '10100', 'SOC0001', 'CAPITAL'),
('COA00096', '10100', 'SOC0001', 'CAPITAL'),
('COA00097', '10100', 'SOC0001', 'CAPITAL'),
('COA00098', '10100', 'SOC0001', 'CAPITAL'),
('COA00099', '10100', 'SOC0001', 'CAPITAL'),
('COA00100', '10100', 'SOC0001', 'CAPITAL'),
('COA00101', '10100', 'SOC0001', 'CAPITAL'),
('COA00102', '10100', 'SOC0001', 'CAPITAL'),
('COA00103', '10100', 'SOC0001', 'CAPITAL'),
('COA00104', '10100', 'SOC0001', 'CAPITAL'),
('COA00105', '10100', 'SOC0001', 'CAPITAL'),