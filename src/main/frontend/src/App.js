import "./App.css";
import React, { useEffect, useState, useCallback } from "react";
import Dropzone, { useDropzone } from "react-dropzone";
import axios from "axios";

const UserProfiles = () => {
  const [userProfiles, setUserProfiles] = useState([]);

  const fetchUserProfiles = () => {
    axios.get("http://localhost:8080/api/v1/user-profile").then((res) => {
      console.log(res);
      setUserProfiles(res.data);
    });
  };

  useEffect(() => {
    fetchUserProfiles();
  }, []);

  return userProfiles.map((userProfile, index) => {
    return (
      <div key={index}>
        {userProfile.userProfileID ? (
          <img
            src={`http://localhost:8080/api/v1/user-profile/${userProfile.userProfileID}/image/download`}
          />
        ) : null}

        <br />
        <br />
        <h1>{userProfile.username}</h1>
        <p>{userProfile.userProfileID}</p>
        <MyDropzone {...userProfile} />
        <br />
      </div>
    );
  });
};

function MyDropzone({ userProfileID }) {
  const onDrop = useCallback((acceptedFiles) => {
    const file = acceptedFiles[0];
    console.log(file);

    const formDataProfile = new FormData();
    formDataProfile.append("file", file);

    axios
      .post(
        `http://localhost:8080/api/v1/user-profile/${userProfileID}/image/upload`,
        formDataProfile,
        {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        }
      )
      .then(() => {
        console.log("file successfully uploaded");
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);
  const { getRootProps, getInputProps, isDragActive } = useDropzone({ onDrop });

  return (
    <div {...getRootProps()}>
      <input {...getInputProps()} />
      {isDragActive ? (
        <p>Drop the image here ...</p>
      ) : (
        <p>Drag 'n' drop profile image here, or click to select files</p>
      )}
    </div>
  );
}

function App() {
  return (
    <div className="App">
      <header>Hallo</header>
      <UserProfiles />
    </div>
  );
}

export default App;
