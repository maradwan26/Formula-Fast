ABOUT:
Furious Fast is an educational game that aims to teach younger grade school students the basic of arithmetic. This is done over a series of 6 levels of increasing difficulty and varying question types including addition, subtraction, multiplication, and division.

DEPENDENCIES:
Apache Maven 3.9.6
JavaFX 13.0
OpenCSV 4.1

HOW TO COMPILE:
1. Download Apache Maven 3.9.6 from their website, select the download from row "Binary zip archive" and column "Link": https://maven.apache.org/download.cgi 
2. Navigate to the top level project directory in your terminal
3. Run the command "mvn clean package"
4. If you want to copy the sample user data into the newly compiled software run the command "cp sample_data.csv target/Data.csv"

HOW TO RUN:
1. A: Navigate to the target file where the compiled jar is stored, if you compiled as shown above that is ./target
   B: If running the compiled version submitted through owl, run a terminal inside the "BUILD" file submitted
2. Run the command "java -cp ./furiousfast-21.0.2.jar group.Main"

**Alternatively, attempt to run from the top level project directory via the command "mvn clean javafx:run", if having issues with the compiled version.

USER GUIDE:
1. Create or log into an account on the initial page, sample account data is provided below
2. If created new account, click on the text boxes one by one to proceed through tutorial
3. Select an unlocked level to enter gameplay, or leaderboard to access the leaderboard page
4. In gameplay, once the timer runs down, type in the answer to the questions that appear near the bottom of the screen in the textbox below them, then click submit or enter.
5. Once level has completed or failed click continue to return to the main menu.
6. Optionally, log out at the end to return to the login page.

INSTRUCTOR MODE:
1. On the inital login page type in the instructor mode password, which is "password123" and click enter.
2. Enter an existing users name in the top search bar, such as any of the sample users given below, and their information will be displayed in the below text box.
3. Click Exit to return to the intial login page.

SAMPLE USERS:
ACCOUNT A: 
    USERNAME:
    PASSWORD:

ACCOUNT B:
    USERNAME:
    PASSWORD:

INSTRUCTOR MODE:
    PASSWORD: password123

**Note sample user data will only be available if game is ran through the provided JAR, or one that has been compiled and ran via the steps above. Running with the alternative method will not include this data.