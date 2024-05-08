function cf_save(filename, s)
% CF_SAVE - Save audio data to file
%
%   CF_SAVE(FILENAME, S) saves audio data specified by the S input argument
%   to a file specified by the FILENAME input argument. The audio file format
%   is determined by the file extension in FILENAME.
%
%   Inputs:
%   - FILENAME: a string that specifies the full or relative path of the file
%   to be saved.
%   - S: a cell array containing the audio data. The first element of the cell array
%   is the audio data matrix, and the second element is the sample frequency in Hertz (Hz).
%
%   Outputs:
%   None.
%
%   Example usage:
%   1. To save an audio matrix variable named S with the audio matrix data 
%   saved as AUDIO and the sampling frequency as 44100hz to a file named
%   "audio.wav" located in the current working directory, type:
%   s = {audio, 44100}
%   cf_save('audio.wav', s)
%
%   Implementation:
%   The CF_SAVE function uses the audiowrite function in MATLAB to write audio
%   data to a file specified by FILENAME. The file format is determined by the
%   file extension in FILENAME. 

audiowrite(filename, s{1}, s{2});
end