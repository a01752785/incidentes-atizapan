import { Input } from "@material-tailwind/react";
import useInput from "../useinput";

export default function Geocoder({ setOutput }) {
  const address = useInput("");

  return (
    <div className="material-icons">
      <Input
        type="text"
        {...address}
        label="Ubicación"
        color="red"
        size="lg"
        icon={
          <svg
            className="text-red-400"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
          >
            <path
              strokeLinecap="round"
              strokeLinejoin="round"
              strokeWidth="2"
              d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z"
            />
            <path
              strokeLinecap="round"
              strokeLinejoin="round"
              strokeWidth="2"
              d="M15 11a3 3 0 11-6 0 3 3 0 016 0z"
            />
          </svg>
        }
      />
      {address.suggestions?.length > 0 && (
        <ul className="mt-2 py-1 px-2 border rounded-md">
          {address.suggestions.map((suggestion, index) => {
            return (
              <li
                key={index}
                className="py-2 rounded-md hover:bg-red-50"
                onClick={() => {
                  address.setValue(suggestion.place_name);
                  address.setSuggestions([]);
                  setOutput(suggestion);
                }}
              >
                {suggestion.place_name}
              </li>
            );
          })}
        </ul>
      )}
    </div>
  );
}
