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
            //TODO : Take the APIKEY out of here
            const endpoint = `https://api.mapbox.com/geocoding/v5/mapbox.places/${event.target.value}.json?access_token=API_KEY&autocomplete=true&country=MX&proximity=-99.267471,19.5562275&limit=3`;
            const response = await fetch(endpoint);
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