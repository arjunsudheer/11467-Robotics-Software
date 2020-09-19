# 11467-Robotics-Software
Team 11467 Software Repository

## Setting up git hub instructions
For Software Github, you all have been added as collaborators, but to actually pull and push (in other words retrieve, edit and publish your code) you need to clone the repository. To do this you can follow a few simple steps. 

*Note: When commands are shown I have put them in quotes. When actually typing these commands in terminal DO NOT put the quotes.

1. Login to GitHub and go to our team repository

2. Click on the green arrow that displays the text “Code”

3. If you have SSH set up then copy the link, other wise use HTTPS. Note that using HTTPS works just as well as SSH, however with HTTPS you will need to enter your GitHub credentials (username and password) each time you want to perform a task.

4. Open terminal and navigate to a folder that you would like to store the repo in. Since we all have mac, use the short cuts “cd” to change your directory. Example usage: Assuming you have a folder called "Robotics" and you would like to enter that folder, the commands you would enter would be "cd Robotics".

5. To actually clone the repo onto your local device, you need to type in the following command: “git clone <url>” where the <url> is the SSH or HTTPS url that you have copied from GitHub. Important Note: Cloning the repo creates a folder with an identical name to our GitHub repo.
  
6. Once you have cloned the repo, navigate to the folder containing the repo. Use the “cd” command to do this. Once inside your repo, you need to pull the existing changes. In terminal type in the command “git pull”. This will pull all the changes made in the remote repository into your local laptop.

7. That is it for now. Follow those steps and you will be set up for GitHub. In the future we will guide you through other actions like committing, branches, pull requests and merge conflicts. If you are stuck or need any help please don’t feel shy to ask the second years. We will be glad to help you out. Great work.

# Important gihub commands

1. <ins>git pull</ins> - This pulls all the files from the remote repo to our local laptop. This is what you need to always do before starting to code as this is how you will have the files needed to work.

2. <ins>git pull origin master</ins> - This is the same as git pull, however despite which branch you are on, you will always get all the files on the master branch.

3.<ins>git add <file></ins> - This adds files. Adding is the first stage you need to do to push your files. "<file>" is the file that you want to get ready to push to the repo. 

4. <ins>git commit -m <text></ins> - This will commit your changes and this the final step need to do. "<text>" is where you will type some text for your commit message. The commit message is where you explain what you are commiting.
Example: You want to commit a file where you wrote software to control the robot with the joy stick. The file is called control.java. Your command line prompt would be "git add control.java". This adds the file that you just worked on. Now you are ready to push this file. Next step is to type "git commit -m "Added file to control the robot with the joystick."". Please note: I have added quotes on the commit message. THESE QUOTES ARE ABSOLUTELY NECCESSARY. You are typing text as a commit message, so it needs to be wrapped in quotes.
  
5. <ins>git push</ins> - This pushes all of the files that you have added/committed to the remote repository. This is the step that you need to take for your code to be displayed later.

6. <ins>git branch <branch name></ins> - This creates a branch for you to work on. Think of a branch as a tree. There is the main trunk (in this case our master branch), then there are many smaller branches to the tree (like how we would use branches to code our seperate thing). It is basically a seperate place for you to do work. Why would you use branches? You can seperate code into different section. For example, you could have a branch that has files to control the robot during the TeleOP period, and another branch for the code to control the robot during the autonomous period. You can create a branch by typing the command "git branch <branch name>" where "<branch name>" is what you would like your branch to be called.
  
7. <ins>git checkout <branch></ins> - This is how you would switch to all of your different branches. "<branch>" is the name of your branch that oyu want to switch to. For example if you are in a branch called "teleOP" and you want to switch to the master branch, you would type "git checkout master". If you want to switch back to the teleOP branch, you would type "git checkout teleOP". 
  
8. <ins>Pull requests</ins> - Pull requests are a way to check eachother's code before officially pushing our code to the repo. In short, it is a double check system. A link will be given in terminal after you push to create a pull request. Please create a pull request for everytime you push to get your code checked before pushing.

9. <ins>Merge conflicts</ins> - This is by far the most complicated concept to learn. To make things simple, a merge conflict is when you are trying to push the code to the remote repo, but your local laptop doesn't have the most recent code. Since our repo has more than one person working on it, there will be pushes at different times, so our local laptop will not always be up-to-date with the remote repo. To resolve this issue, you need to use the "git pull origin master" or "git pull command" (git pull only if your current branch is master) and that will retrieve all files so you are up-to-date. Now you are ready to push your code.

# Basic command line tools

1. <ins>cd</ins> - Changes your current directory to the one specified.

2. <ins>mkdir</ins> - Creates a folder of the name specified

3. <ins>touch</ins> - Creates a file of the name specified

# Meeting Notes

We should keep software meeting note logs in this readme.md file. It will be a good show to the judges to show our organization as a team and it will serve as a nice way to keep track of our progress here as well.
