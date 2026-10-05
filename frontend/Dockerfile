# Use the same Node.js version as the host; this is a local development image.
FROM node:24.16.0-alpine
WORKDIR /app
# Align npm with the installed host version and install the locked dependency tree.
RUN npm install -g npm@11.13.0
COPY package.json package-lock.json ./
RUN npm ci
COPY . .
# Validate the production bundle before launching the development server.
RUN npm run build && chown -R node:node /app
USER node
EXPOSE 5173
CMD ["npm", "run", "dev"]
