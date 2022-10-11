import { useState } from "react";

const useInput = (initialValue) => {
    const [value, setValue] = useState(initialValue);
    const [suggestions, setSuggestions] = useState([]);

    const handleChange = async (event) => {
        setValue(event.target.value);
        if (event.target.value.length < 5) {
            setSuggestions([]);
            return;
        } 
        try{
            const response = await fetch("/api/geohelper", {method : "POST", body : event.target.value});
            const results = await response.json();
            setSuggestions(results?.features)
        }
        catch (err) {
            console.log("Error fetching data ", err);
        }
    };

    return {
        value,
        onChange : handleChange,
        setValue,
        suggestions,
        setSuggestions
    };
};

export default useInput;