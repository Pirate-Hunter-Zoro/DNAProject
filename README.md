# CSCI 311 - Design and Analysis of Algorithms
Bucknell University <br />
Lewisburg, PA

### Course Info
Instructor: Edward Talmage
Semester: Fall 2022

## Team Information
Mikey Ferguson <br />
Alex Luzetsky <br />

## Project Information
*This project implements three different matching algorithms to find the closest match of a query DNA sequence to a database of known sequences. The three algorithmic methods are lining the query against each sequence in the database by longest common subsequence, the Needleman-Wunsch Algorithm, and by the longest common substring. Once aligned, the common characters between the two DNA strings is counted.*

## How to run it
*Navigate to a directory on your computer in which you want the project contents, and run the following command in your terminal:<br>*
git clone https://github.com/Pirate-Hunter-Zoro/DNAProject.git <br>
*A folder named DNAProject should have been created in your current directory. Enter this folder via terminal and run the following command:<br>*
gradle wrapper <br>
*Once this operation finishes, run the following command to run the application: <br>*
./gradlew run <br>
*NOTE - when selecting a database file, use DNA_sequences.txt in the root project folder, and any of the test-.txt files for the query file. <br>*