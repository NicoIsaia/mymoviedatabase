/**
 * Author:  NicoIsaia
 * Created: 20 mar. 2024
 */

CREATE TABLE IF NOT EXISTS movies (
    id IDENTITY PRIMARY KEY,
    title VARCHAR(30) NOT NULL,
    year NUMBER NOT NULL,
    genres VARCHAR(255) /*just use CSV to store different genres -
    keep adding class fields*/
    
);